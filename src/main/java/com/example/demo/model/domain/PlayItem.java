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

    @Id
    @Column(name = "play_group_id")
    private Long playGroupId;

    @Id
    @Column(name = "song_id")
    private Long songId;

    /** 해당하는 그룹 정보. */
    @ManyToOne
    @JoinColumn(name = "play_group_id", insertable = false, updatable = false)
    private PlayGroup playGroup;

    /** song ID */
    @ManyToOne
    @JoinColumn(name = "song_id", insertable = false, updatable = false)
    private Song song;

}
