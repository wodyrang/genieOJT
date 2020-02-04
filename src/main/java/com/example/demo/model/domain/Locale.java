package com.example.demo.model.domain;

import com.example.demo.code.enums.LocaleType;
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
@Table(name = "locale")
@IdClass(value = LocaleId.class)
@NoArgsConstructor
@AllArgsConstructor
public class Locale extends BaseTimeEntity {

    /** 앨범정보 */
    @Id
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    /** 지역코드 */
    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(name = "locale_type")
    private LocaleType localeType;
}
