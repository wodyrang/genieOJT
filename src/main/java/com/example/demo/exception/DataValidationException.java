package com.example.demo.exception;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.validation.BindingResult;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 *
 * 데이터 검증 오류시 발생하는 예외.
 */
@Getter
public class DataValidationException extends RuntimeException {

    private final BindingResult bindingResult;


    /**
     * 데이터 검증 예외 생성
     * @param bindingResult 검증 오류 정보.
     */
    public DataValidationException(@NonNull final BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }
}
