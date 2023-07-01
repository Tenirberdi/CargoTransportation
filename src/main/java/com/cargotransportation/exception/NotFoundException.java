package com.cargotransportation.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException{
    public NotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
