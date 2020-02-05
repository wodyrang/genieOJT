package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 * Play list에서 삭제요청할 노래 ID
 */
@Getter
@Setter
public class DeletePlayItem {

    @NotNull(message = "사용자 ID는 필수입니다.")
    @JsonProperty(value = "id")
    private Long userId;

    @NotNull(message = "Playlist를 선택해 주세요.")
    @JsonProperty(value = "playListId")
    private Long playGroupId;

    @JsonProperty(value = "songIds")
    private List<Long> songs;
}
