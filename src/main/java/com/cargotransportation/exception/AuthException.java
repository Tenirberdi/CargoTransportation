package com.cargotransportation.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends BaseException {
    public AuthException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
