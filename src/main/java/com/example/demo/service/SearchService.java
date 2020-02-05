package com.example.demo.service;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.model.data.AlbumResult;
import com.example.demo.model.data.SearchResult;
import com.example.demo.model.data.SongResult;
import com.example.demo.model.domain.Album;
import com.example.demo.model.domain.Song;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wody8674@gmail.com on 2020/01/31.
 *
 * 검색 처리를 위한 Service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    /** 객체 매핑 클레스 */
    private final ModelMapper modelMapper;

    /** 앨범 관련 Service */
    private final AlbumService albumService;

    /** 노래 관련 Service */
    private final SongService songService;


    /**
     * 앨범 / 노래 검색 처리
     * @param title 앨범 / 노래 제목
     * @param localeType 재생 가능 지역
     * @return 검색된 앨범, 노래 목록
     */
    @Transactional(readOnly = true)
    public SearchResult searchAlbum(final String title, final LocaleType localeType) {
        final SearchResult searchResult = new SearchResult();

        // 앨범 검색.
        final List<Album> searchedAlbumList = this.albumService.findByTitle(title, localeType);
        if (CollectionUtils.isNotEmpty(searchedAlbumList)) {
            searchResult.setAlbums(
                    searchedAlbumList.stream()
                            .map(album -> this.modelMapper.map(album, AlbumResult.class))
                            .collect(Collectors.toList())
            );
        }

        // 노래 검색.
        final List<Song> searchedSongList = this.songService.findByTitle(title, localeType);
        if (CollectionUtils.isNotEmpty(searchedSongList)) {
            searchResult.setSongs(
                    searchedSongList.stream()
                            .map(song -> this.modelMapper.map(song, SongResult.class))
                            .collect(Collectors.toList())
            );
        }

        return searchResult;
    }


}
