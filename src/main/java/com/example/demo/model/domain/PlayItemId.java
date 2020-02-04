package com.example.demo.model.domain;

import lombok.*;

import java.io.Serializable;


/**
 * Created by wody8674@gmail.com on 2020/02/04.
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PlayItemId implements Serializable {

    private Long playGroupId;
    private Long songId;
}
