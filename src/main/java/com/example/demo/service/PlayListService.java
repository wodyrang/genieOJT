package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.data.CreatePlayList;
import com.example.demo.model.data.PlayGroupListResult;
import com.example.demo.model.data.PlayGroupSongResult;
import com.example.demo.model.domain.*;
import com.example.demo.repository.PlayGroupRepository;
import com.example.demo.repository.PlayItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 *
 * Play List 관련 Service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlayListService {

    private final ModelMapper modelMapper;

    private AlbumService albumService;
    private SongService songService;

    private final PlayGroupRepository playGroupRepository;
    private final PlayItemRepository playItemRepository;


    /**
     * 그룹 이름 중복 체크
     * @param userId 사용자 ID
     * @param groupName 그룹 이름
     * @return 중복 여부.
     */
    @Transactional(readOnly = true)
    public boolean checkDuplicateGroupName(final Long userId, final String groupName) {
        return this.playGroupRepository.countByUserIdAndGroupName(userId, groupName) > 0;
    }


    /**
     * 사용자의 모든 Play list 목록 조회
     * @param userId 사용자 ID
     * @return 목록.
     */
    @Transactional(readOnly = true)
    public List<PlayGroupListResult> getAllPlayGroupList(final Long userId) {
        final List<PlayGroup> playGroupList = this.playGroupRepository.findPlayGroupsByUserId(userId);
        if (CollectionUtils.isEmpty(playGroupList)) {
            return null;
        }

        return playGroupList.stream()
                .map(playGroup -> this.modelMapper.map(playGroup, PlayGroupListResult.class))
                .collect(Collectors.toList());
    }


    /**
     * 조회한 play lsit의 노래 목록 조회
     * @param userId 사용자 ID
     * @param playGroupId id
     * @return 노래 목록.
     */
    @Transactional(readOnly = true)
    public List<PlayGroupSongResult> getSongListFromPlayGroup(final Long userId, final Long playGroupId) {
        final PlayGroup playGroup = this.playGroupRepository.findPlayGroupByUserIdAndPlayGroupId(userId, playGroupId);
        if (playGroup == null) {
            return null;
        }

        final List<PlayItem> itemList = playGroup.getPlayItemList();
        if (CollectionUtils.isEmpty(itemList)) {
            return null;
        }

        return itemList.stream()
                .map(item -> {
                    final Song song = item.getSong();
                    final PlayGroupSongResult result = this.modelMapper.map(song, PlayGroupSongResult.class);

                    result.setPlayGroupId(playGroupId);

                    return result;
                })
                .collect(Collectors.toList());
    }


    /**
     * play list 저장 처리.
     * @param createPlayList play list info
     */
    @Transactional(rollbackFor = Exception.class)
    public void savePlayList(final CreatePlayList createPlayList) {
        if (createPlayList == null) {
            throw new BadRequestException();
        }

        final Long playGroupId = createPlayList.getPlayGroupId();
        final String groupName = createPlayList.getGroupName();

        // ############################################################
        // 그룹 정보가 기존에 존재하면 조회하고 없으면 새로 생성함.
        // ############################################################
        PlayGroup playGroup = null;
        if (playGroupId != null && playGroupId > 0) {
            playGroup = this.playGroupRepository.findById(playGroupId).orElse(null);
        }
        if (playGroupId == null || playGroup == null) {
            playGroup = PlayGroup.builder().groupName(groupName).build();
        }

        // 그룹 저장.
        if (StringUtils.isNotBlank(groupName)) {
            playGroup.updateGroupName(groupName);
        }
        this.playGroupRepository.save(playGroup);

        // ############################################################
        // 그룹에 노래 저장
        // ############################################################
        final PlayGroup savedPlayGroup = playGroup;
        final List<Long> reqSongIdList = new ArrayList<>();
        final Long albumId = createPlayList.getAlbumId();

        // 선택한 앨범의 노래 조회하여 등록.
        if (albumId != null && albumId > 0) {
            final Album album = this.albumService.findById(albumId);
            if (album != null) {
                final List<Song> songList = album.getSongList();
                if (CollectionUtils.isNotEmpty(songList)) {
                    reqSongIdList.addAll(
                            songList.stream()
                                    .map(Song::getSongId)
                                    .collect(Collectors.toList()));
                }
            }
        }

        // 개별 선택된 노래 등록.
        reqSongIdList.addAll(createPlayList.getSongList());

        // Play list에 노래 저장.
        if (CollectionUtils.isNotEmpty(reqSongIdList)) {
            final List<PlayItem> playItemList =
                    reqSongIdList.stream()
                            .map(id -> {
                                final Song song = Song.builder().songId(id).build();
                                return PlayItem.builder().playGroup(savedPlayGroup).song(song).build();
                            })
                            .collect(Collectors.toList());

            this.playItemRepository.saveAll(playItemList);
        }
    }


    /**
     * 요청한 Play list 삭제
     * @param userId 사용자 ID
     * @param playGroupId 그룹 ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deletePlayList(final Long userId, final Long playGroupId) {
        final PlayGroup playGroup = this.playGroupRepository.findById(playGroupId).orElse(null);
        if (playGroup == null) {
            throw new NotFoundException("해당 Play list를 찾을 수 없습니다.");
        }
        if (!playGroup.getUserId().equals(userId)) {
            throw new NotFoundException("해당 Play list를 찾을 수 없습니다.");
        }

        // 모든 노래 삭제
        this.playItemRepository.deleteByPlayGroup(playGroup);

        // play list 삭제.
        this.playGroupRepository.delete(playGroup);
    }


    /**
     * 선택한 노래 삭제.
     * @param userId 사용자 ID
     * @param playGroupId 그룹 ID
     * @param songIdList 선택한 노랙 ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deletePlayListSongs(final Long userId, final Long playGroupId, final List<Long> songIdList) {
        final PlayGroup playGroup = this.playGroupRepository.findById(playGroupId).orElse(null);
        if (playGroup == null) {
            throw new NotFoundException("해당 Play list를 찾을 수 없습니다.");
        }
        if (!playGroup.getUserId().equals(userId)) {
            throw new NotFoundException("해당 Play list를 찾을 수 없습니다.");
        }

        if (CollectionUtils.isEmpty(songIdList)) {
            if (log.isWarnEnabled()) {
                log.warn("삭제 요청한 Song ID값이 없습니다.");
                log.warn("userId -> {}, playGroupId -> {}", userId, playGroupId);
            }
            return ;
        }

        // 삭제 요청할 노래 정보 목록 생성.
        final List<PlayItem> itemList = songIdList.stream()
                .map(songId -> {
                    final Song song = this.songService.findById(songId);
                    if (song == null) {
                        return null;
                    }

                    return PlayItem.builder().playGroup(playGroup).song(song).build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 노래 삭제.
        this.playItemRepository.deleteAll(itemList);
    }

}
