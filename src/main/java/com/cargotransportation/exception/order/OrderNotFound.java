package com.cargotransportation.exception.order;

import com.cargotransportation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OrderNotFound extends BaseException {

    public OrderNotFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
