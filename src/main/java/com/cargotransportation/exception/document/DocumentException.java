package com.cargotransportation.exception.document;

import com.cargotransportation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DocumentException extends BaseException {
    public DocumentException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
