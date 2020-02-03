package com.example.demo.exception;

/**
 * Created by wody8674@gmail.com on 2020/01/31.
 *
 * 400 오류 발생을 위한 Exception.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
