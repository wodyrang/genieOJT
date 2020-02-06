package com.example.demo.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by wody8674@gmail.com on 2020/02/04.
 */
@Getter
@Setter
public class AlbumJson {

    @JsonProperty(value = "album_title")
    private String albumTitle;

    private List<String> locales;

    private List<SongJson> songs;

}
