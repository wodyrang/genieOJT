package com.example.demo.service;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.model.domain.Song;
import com.example.demo.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wody@genieworks.net on 2020/01/31.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SongService {

    /** 노래 저장소. */
    private final SongRepository songRepository;


    /**
     * 노래제목으로 노래 목록 조회.
     * @param title 노래 제목
     * @param localeType 재생 가능 지역
     * @return 노래 목록.
     */
    @Transactional(readOnly = true)
    public List<Song> findByTitle(final String title, final LocaleType localeType) {
        return this.songRepository.findByTilte(title, localeType);
    }

}
