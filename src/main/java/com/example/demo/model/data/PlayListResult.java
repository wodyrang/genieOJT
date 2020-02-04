package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 *
 * Play lsit 조회 결과.
 */
@Getter
@Setter
public class PlayListResult {

    /** play group ID */
    @JsonProperty(value = "playListId")
    private Long playGroupId;

    /** 그룹 이름. */
    @JsonProperty(value = "playListName")
    private String groupName;

    /** 사용자 ID */
    private Long userId;

    /** 등록된 노래 정보 목록. */
    private List<SongResult> songs;
}
