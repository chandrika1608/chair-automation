package com.lixo.pos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaxNotFoundException extends RuntimeException {
    public TaxNotFoundException(String message) {
        super(message);
    }

    public TaxNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}

