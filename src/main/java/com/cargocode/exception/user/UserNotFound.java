package com.cargocode.exception.user;

import com.cargocode.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFound extends BaseException {
    public UserNotFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
