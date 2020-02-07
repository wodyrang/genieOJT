package com.example.demo.service;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.model.data.*;
import com.example.demo.model.domain.Album;
import com.example.demo.model.domain.Locale;
import com.example.demo.model.domain.Song;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.LocaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 *
 * 앨범 관련 처리 Service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumService {

    @Value(value = "${api.service.domain}")
    private String serviceUrl;

    private final ModelMapper modelMapper;

    /** 앨범 저장소. */
    private final AlbumRepository albumRepository;
    /** 재생 지역 정보 저장소. */
    private final LocaleRepository localeRepository;

    /** 노래 관련 처리 Service */
    private final SongService songService;


    /**
     * ID로 앨범 정보 조회.
     * @param albumId 앨범 ID
     * @return 앨범 정보.
     */
    @Transactional(readOnly = true)
    public Album findById(final Long albumId) {
        return this.albumRepository.findById(albumId).orElse(null);
    }


    /**
     * 앨범 제목으로 앨범 검색
     * @param title 앨범 제목
     * @param localeType 지역정보
     * @return 검색된 앨범 목록.
     */
    @Transactional(readOnly = true)
    public List<Album> findByTitle(final String title, final LocaleType localeType) {
        return this.albumRepository.searchAlbumByTitle(title, localeType);
    }


    /**
     * 앨범 목록 페이징 처리 조회
     * @param pageable 조회 페이징 정보
     * @return 앨범 목록.
     */
    @Transactional(readOnly = true)
    public AlbumsResult findAlbumsResult(final PageRequest pageable) {
        final Page<AlbumResult> albumPage =
                this.albumRepository
                        .findAll(pageable.of())
                        .map(album -> this.modelMapper.map(album, AlbumResult.class));

        final List<AlbumResult> albumList = albumPage.getContent();
        final int total = albumPage.getTotalPages();
        final AlbumsResult albumsResult = new AlbumsResult();

        if (CollectionUtils.isNotEmpty(albumList)) {
            albumsResult.setAlbums(albumList);
            albumsResult.setPages(new Pages(pageable.getPage(), total, this.serviceUrl + "/albums"));
        }

        return albumsResult;
    }


    /**
     * 앨범 등록.
     * @param album 앨범 정보.
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveAlbum(final Album album) {
        this.albumRepository.save(album);
    }


    /**
     * 앨범 정보 저장 처리.
     * @param albumJsons 앨범 정보.
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveAllAlbumInfo(final List<AlbumJson> albumJsons) {
        for (AlbumJson albumJson : albumJsons) {
            final List<String> locales = albumJson.getLocales();
            final List<SongJson> songJsonList = albumJson.getSongs();

            // 앨범저장.
            final Album album = Album.builder().albumTitle(albumJson.getAlbumTitle()).build();
            this.saveAlbum(album);

            // 재생지역 저장.
            final List<Locale> localeList = locales.stream()
                    .map(locale -> {
                        final LocaleType type = LocaleType.findType(locale);
                        return Locale.builder().albumId(album.getAlbumId()).localeType(type).build();
                    })
                    .collect(Collectors.toList());

            this.localeRepository.saveAll(localeList);

            // 노래 저장.
            for (SongJson songJson : songJsonList) {
                final Song song = Song.builder()
                        .album(album)
                        .title(songJson.getTitle())
                        .length(songJson.getLength())
                        .track(songJson.getTrack())
                        .build();

                this.songService.saveSong(song);
            }
        }
    }


}
