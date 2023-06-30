package com.cargotransportation.exception.user;

import com.cargotransportation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFound extends BaseException {
    public UserNotFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
