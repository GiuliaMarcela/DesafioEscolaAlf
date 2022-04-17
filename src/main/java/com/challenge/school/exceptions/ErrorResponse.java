package com.challenge.school.exceptions;

import lombok.Data;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private boolean failed;
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String path;
    private String message;

    public ErrorResponse(HttpStatus status, String path, Exception exception) {
        this.failed = true;
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.path = path;
        this.message = exception.getMessage();
    }
}
