package com.cargotransportation.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(NotFoundException e){
        return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
    }

    @ExceptionHandler(IsExistsException.class)
    public ResponseEntity<?> isExists(IsExistsException e){
        return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
    }

    @ExceptionHandler(IllegalStatusException.class)
    public ResponseEntity<?> illegalStatus(IllegalStatusException e){
        return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
    }

}
