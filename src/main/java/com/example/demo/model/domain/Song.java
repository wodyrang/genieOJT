package com.example.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
@Getter
@Builder
@Entity
@Table(name = "song")
@NoArgsConstructor
@AllArgsConstructor
public class Song extends BaseTimeEntity {

    /** 곡 ID */
    @Id
    @Column(name = "song_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    /** 곡 제목 */
    @Column(name = "title", length = 200, nullable = false)
    private String title;

    /** 곡 재생 길이 */
    @Column(name = "length", nullable = false)
    private Integer length;

    /** 트랙 번호 */
    @Column(name = "track", nullable = false)
    private Integer track;

    /** 곡의 앨범정보 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false, updatable = false)
    private Album album;
}
