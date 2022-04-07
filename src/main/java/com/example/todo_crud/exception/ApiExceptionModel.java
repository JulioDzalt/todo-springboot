package com.example.todo_crud.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiExceptionModel {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiExceptionModel(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super();
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

}