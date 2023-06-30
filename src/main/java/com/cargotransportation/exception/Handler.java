package com.cargotransportation.exception;

import com.cargotransportation.exception.role.RoleNotFound;
import com.cargotransportation.exception.user.UserIsExists;
import com.cargotransportation.exception.user.UserNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> userNotFound(UserNotFound e){
        return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
    }

    @ExceptionHandler(UserIsExists.class)
    public ResponseEntity<?> userIsExists(UserIsExists e){
        return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
    }

    @ExceptionHandler(RoleNotFound.class)
    public ResponseEntity<?> roleNotFound(RoleNotFound e){
        return new ResponseEntity<>(e.getMessage(),e.getHttpStatus());
    }

}
