package com.cargotransportation.exception;

import com.cargotransportation.constants.ResponseState;
import com.cargotransportation.dto.response.Response;
import com.cargotransportation.dto.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.cargotransportation.constants.DetailCodeEnum.DUPLICATE_ENTRY;
import static com.cargotransportation.constants.DetailCodeEnum.FILE_LOAD_ERROR;
import static com.cargotransportation.constants.DetailCodeEnum.UNDOCUMENTED_ERROR;
import static com.cargotransportation.constants.ResponseState.FAIL;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> userNotFound(NotFoundException e){
        return new ResponseEntity<>(handleResponse(UNDOCUMENTED_ERROR.getCode(), e.getMessage()),
                HttpStatus.OK);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Response> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(handleResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FileLoadException.class)
    public ResponseEntity<Response> handleFileLoadException(FileLoadException e) {
        return new ResponseEntity<>(handleResponse(FILE_LOAD_ERROR.getCode(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<Response> handleInvalidStatusException(InvalidStatusException e){
        return new ResponseEntity<>(handleResponse(UNDOCUMENTED_ERROR.getCode(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response> handleMaxSizeException(MaxUploadSizeExceededException e) {
        return new ResponseEntity<>(handleResponse(FILE_LOAD_ERROR.getCode(), e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<Response> handleDuplicateEntryException(DuplicateEntryException e) {
        return new ResponseEntity<>(handleResponse(DUPLICATE_ENTRY.getCode(), e.getMessage()),
                HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        return new ResponseEntity<>(handleResponse(UNDOCUMENTED_ERROR.getCode(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Response handleResponse(int messageCode, String message) {
        return Response.builder()
                .state(FAIL)
                .errorCode(messageCode)
                .message(message).build();
    }

}
