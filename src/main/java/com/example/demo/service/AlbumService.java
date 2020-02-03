package com.example.demo.service;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.model.domain.Album;
import com.example.demo.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wody@genieworks.net on 2020/01/31.
 *
 * 앨범 관련 처리 Service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumService {

    /** 앨범 저장소. */
    private final AlbumRepository albumRepository;


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







}
