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
 * Created by wody8674@gmail.com on 2020/01/31.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SongService {

    /** 노래 저장소. */
    private final SongRepository songRepository;


    /**
     * ID로 노래정보 조회
     * @param sondId 노래 ID
     * @return 노래정보.
     */
    @Transactional(readOnly = true)
    public Song findById(final Long sondId) {
        return this.songRepository.findById(sondId).orElse(null);
    }


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


    /**
     * 노래 저장.
     * @param song 노래 정보.
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveSong(final Song song) {
        this.songRepository.save(song);
    }

}
