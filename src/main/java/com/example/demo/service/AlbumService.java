package com.example.demo.service;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.model.data.AlbumResult;
import com.example.demo.model.data.AlbumsResult;
import com.example.demo.model.data.Pages;
import com.example.demo.model.domain.Album;
import com.example.demo.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public AlbumsResult findAlbumsResult(final Pageable pageable) {
        final Page<AlbumResult> albumPage =
                this.albumRepository
                        .findAll(pageable)
                        .map(album -> this.modelMapper.map(album, AlbumResult.class));

        final List<AlbumResult> albumList = albumPage.getContent();
        final int total = albumPage.getTotalPages();
        final AlbumsResult albumsResult = new AlbumsResult();

        if (CollectionUtils.isNotEmpty(albumList)) {
            albumsResult.setAlbums(albumList);
            albumsResult.setPages(new Pages(pageable.getPageNumber(), total, this.serviceUrl));
        }

        return albumsResult;
    }


}
