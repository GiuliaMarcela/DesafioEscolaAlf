package com.challenge.school.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class CustomInternalServerException extends RuntimeException{
    public CustomInternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
