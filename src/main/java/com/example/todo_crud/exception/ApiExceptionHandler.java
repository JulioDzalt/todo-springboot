package com.example.todo_crud.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { ApiRequestException.class })
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        // 1 Payload cotaining exception detaills
        // ApiException apiExeption = new ApiException(e.getMessage(),
        // HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        // 2 Return response entity
        // return new ResponseEntity<>(apiExeption, HttpStatus.BAD_REQUEST);

        // 1 Payload cotaining exception detaills

        ApiExceptionModel apiExeption = new ApiExceptionModel(
            e.getMessage(), e.getHttpStatus(), ZonedDateTime.now(ZoneId.of("Z"))
        );

        // 2 Return response entity
        return new ResponseEntity<>(apiExeption, apiExeption.getHttpStatus());

    }
}