package com.cargotransportation.exception;

import org.springframework.http.HttpStatus;

public class IllegalStatusException extends BaseException{
    public IllegalStatusException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
