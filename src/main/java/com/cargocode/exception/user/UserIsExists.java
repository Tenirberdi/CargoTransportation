package com.cargocode.exception.user;

import com.cargocode.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserIsExists extends BaseException {
    public UserIsExists(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
