package com.example.demo.code.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Created by wody@genieworks.net on 2020/01/31.
 */
@Getter
@AllArgsConstructor
public enum LocaleType {

    ALL("all", "전체"),
    KO("ko", "한국어"),
    EN("en", "영어"),
    JA("ja", "일본어");

    private final String code;
    private final String typeName;


    /**
     * Code값을 입력하여 해당 Type을 구함.
     * @param code 코드
     * @return LocaleType
     */
    public static LocaleType findType(final String code) {
        for (LocaleType type : LocaleType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }

        return null;
    }

}
