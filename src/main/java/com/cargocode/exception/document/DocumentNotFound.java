package com.cargocode.exception.document;

import com.cargocode.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DocumentNotFound extends BaseException {
    public DocumentNotFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
