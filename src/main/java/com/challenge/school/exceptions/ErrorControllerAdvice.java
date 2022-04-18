package com.challenge.school.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleCustomBadRequestException(
        CustomBadRequestException exception, HttpServletRequest http
    ) {
        String exceptionName = exception.getClass().getName();

        return ResponseEntity
                .status(BAD_REQUEST)
                .header("exception-name", exceptionName)
                .header("exception-message", exception.getMessage())
                .body(new ErrorResponse(BAD_REQUEST, http.getRequestURI(), exception));
    }
}
