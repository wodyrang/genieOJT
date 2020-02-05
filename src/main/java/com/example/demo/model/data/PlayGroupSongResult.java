package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 */
@Getter
@Setter
public class PlayGroupSongResult {

    @JsonProperty(value = "playListId")
    private Long playGroupId;

    /** 노래 ID */
    private Long songId;

    /** 노래 제목 */
    private String title;

    /** 노래 길이 */
    private Integer length;

    /** 트랙 번호 */
    private Integer track;

    /** 등록 일시 */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdDate;

    /** 수정 일시 */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime modifiedDate;

}
