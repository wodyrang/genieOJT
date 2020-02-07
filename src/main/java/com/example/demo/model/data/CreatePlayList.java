package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 *
 * Play list를 생성하기 위한 요청 Data.
 */
@Getter
@Setter
public class CreatePlayList {

    /** 노래 추가 요청할 playlistID */
    @JsonProperty(value = "playListId")
    private Long playGroupId;

    /** 저장할 그룹 이름 */
    @JsonProperty(value = "playListName")
    private String groupName;

    /** 사용자 ID */
    @NotNull(message = "사용자 ID는 필수입니다.")
    @JsonProperty(value = "id")
    private Long userId;

    /** 추가할 앨범 ID */
    private Long albumId;

    /** 저장할 songId 목록, 생략가능함. */
    @JsonProperty(value = "songs")
    private List<Long> songList;
}
