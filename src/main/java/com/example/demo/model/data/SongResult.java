package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 *
 * 노래 검색 결과.
 */
@Getter
@Setter
public class SongResult {

    /** 노래 ID */
    @JsonProperty(value = "id")
    private Long songId;

    /** 노래 제목 */
    private String title;

    /** 노래 길이 */
    private Integer length;

    /** 트랙 번호 */
    private Integer track;
}
