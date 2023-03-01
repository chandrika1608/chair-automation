package com.lixo.pos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KitchenNotFoundException extends RuntimeException {
    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}

