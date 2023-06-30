package com.cargotransportation.exception.role;

import com.cargotransportation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class RoleNotFound extends BaseException {
    public RoleNotFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
