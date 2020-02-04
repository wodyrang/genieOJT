package com.example.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 */
@Getter
@Entity
@Table(name = "play_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayGroup extends BaseTimeEntity {


    /** play group ID */
    @Id
    @Column(name = "play_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playGroupId;

    /** 그룹 이름. */
    @Column(name = "group_name", length = 200, nullable = false)
    private String groupName;

    /** 사용자 ID */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 해당 그룹의 Play item */
    @OneToMany(mappedBy = "playGroup", fetch = FetchType.LAZY)
    private List<PlayItem> playItemList;


    /**
     * 그룹 이름 변경.
     * @param groupName 변경할 그룹 이름.
     */
    public void updateGroupName(final String groupName) {
        this.groupName = groupName;
    }
}
