package com.customer.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException() {
        super();
    }
    public RecordNotFoundException(String message) {
        super(message);
    }
    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
