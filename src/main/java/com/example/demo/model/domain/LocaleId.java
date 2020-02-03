package com.example.demo.model.domain;

import com.example.demo.code.enums.LocaleType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * Created by wody8674@gmail.com on 2020/01/31.
 */
@Getter
@Setter
@EqualsAndHashCode
public class LocaleId implements Serializable {

    private Long albumId;
    private LocaleType localeType;
}
