package com.cargotransportation.exception;

import org.springframework.http.HttpStatus;

public class IsExistsException extends BaseException{
    public IsExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
