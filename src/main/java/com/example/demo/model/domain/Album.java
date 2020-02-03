package com.example.demo.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
@Getter
@Entity
@Table(name = "album")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album extends BaseTimeEntity {


    /** 앨범 ID */
    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    /** 앨범 타이틀 */
    @Column(name = "album_title", length = 200, nullable = false)
    private String albumTitle;

    /** 앨범*/
    @OneToMany(mappedBy = "albumId", fetch = FetchType.LAZY)
    private List<Locale> locales;

    /** 곡 정보 */
    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private List<Song> songs;

}
