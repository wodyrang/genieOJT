package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
@Getter
@Setter
public class AlbumResult {

    /** 앨범 ID */
    @JsonProperty(value = "id")
    private Long albumId;

    /** 앨범 타이틀 */
    @JsonProperty(value = "title")
    private String albumTitle;

    /** 앨범의 노래 목록. */
    @JsonProperty(value = "songs")
    private List<SongResult> songList;
}
