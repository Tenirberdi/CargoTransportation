package com.cargocode.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
    public BaseException(String message, HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
