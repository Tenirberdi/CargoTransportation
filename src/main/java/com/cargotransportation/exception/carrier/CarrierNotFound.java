package com.cargotransportation.exception.carrier;

import com.cargotransportation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CarrierNotFound extends BaseException {
    public CarrierNotFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
