package com.challenge.school.exceptions;

public class CustomInternalServerException extends RuntimeException{
    public CustomInternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
