package com.cargotransportation.exception;

import org.springframework.http.HttpStatus;

public class InvalidStatusException extends BaseException{
    public InvalidStatusException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
