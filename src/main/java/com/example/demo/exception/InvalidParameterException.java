package com.example.demo.exception;


/**
 * Created by wody8674@gmail.com on 2020/02/03.
 */
public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }
}
