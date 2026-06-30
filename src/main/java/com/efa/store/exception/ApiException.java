package com.efa.store.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final String errorCode;

    public ApiException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
