package com.example.todo_crud.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {

    
    private HttpStatus httpStatus;

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = HttpStatus.BAD_GATEWAY;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    // public void setHttpStatus(HttpStatus httpStatus) {
    //     this.httpStatus = httpStatus;
    // }

}