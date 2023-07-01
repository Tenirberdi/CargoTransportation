package com.cargotransportation.exception.productType;

import com.cargotransportation.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ProductTypeNotFound extends BaseException {
    public ProductTypeNotFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
