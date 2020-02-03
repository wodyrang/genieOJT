package com.example.demo.model.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 *
 * 앨범/노래 검색 결과
 */
@Getter
@Setter
public class SearchResult {

    /** 앨범 검색 결과 목록. */
    private List<AlbumResult> albums;

    /** 노래 검색 결과 목록. */
    private List<SongResult> songs;
}
