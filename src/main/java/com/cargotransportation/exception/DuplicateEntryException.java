package com.cargotransportation.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(String message) {
        super(message);
    }
}
