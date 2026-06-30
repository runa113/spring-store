package com.efa.store.exception;

import com.efa.store.constants.ErrorMessages;

public class ResourceNotFoundException extends ApiException{
    public ResourceNotFoundException(String message) {
        super(ErrorMessages.NOT_FOUND, message);
    }

}
