package com.example.demo.exception;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 *
 * 404 처리.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
