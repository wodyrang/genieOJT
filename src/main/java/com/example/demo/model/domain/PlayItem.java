package com.example.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 *
 * 사용자의 Play list ITEM.
 */
@Getter
@Entity
@Table(name = "play_item")
@IdClass(value = PlayItemId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayItem {

    /** 해당하는 그룹 정보. */
    @Id
    @ManyToOne
    @Column(name = "play_group_id")
    private PlayGroup playGroup;

    /** song ID */
    @Id
    @ManyToOne
    @Column(name = "song_id")
    private Song song;

}
