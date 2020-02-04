package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 *
 * Play list 목록 조회 결과.
 */
@Getter
@Setter
public class PlayGroupListResult {

    @JsonProperty(value = "playListId")
    private Long playGroupId;

    @JsonProperty(value = "playListName")
    private String groupName;
}
