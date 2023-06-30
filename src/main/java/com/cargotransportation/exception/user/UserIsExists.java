package com.cargotransportation.exception.user;

import com.cargotransportation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserIsExists extends BaseException {
    public UserIsExists(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
