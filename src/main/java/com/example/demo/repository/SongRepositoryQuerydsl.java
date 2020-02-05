package com.example.demo.repository;

import com.example.demo.code.enums.LocaleType;
import com.example.demo.model.domain.Song;

import java.util.List;

/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
public interface SongRepositoryQuerydsl {


    /**
     * 제목으로 노래 검색
     * @param title 노래 제목
     * @param localeType 재생 가능 지역
     * @return 검색된 노래 목록.
     */
    List<Song> findByTilte(final String title, final LocaleType localeType);

}
