package com.example.todo_crud.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.todo_crud.responses.ErrorResponse;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        // Map<String, List<String>> body = new HashMap<>();
        // body.put("errors", errors);
        // return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        ErrorResponse error = new ErrorResponse();
        error.setMessage(errors.get(0));
        error.setCode("400");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
    

    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        System.out.println(ex.getClass()+"\n\n");
        System.out.println(ex.getCause() +"\n\n");
        System.out.println(ex.getCause().getMessage() +"\n\n");
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getCause().getMessage());

        // 2 Return response entity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { RuntimeException.class, Exception.class })
    protected ResponseEntity<Object> handleUnknow(
      RuntimeException ex, WebRequest request) {
          // String bodyOfResponse = "This should be application specific";
          // return handleExceptionInternal(ex, bodyOfResponse, 
          //   new HttpHeaders(), HttpStatus.CONFLICT, request);

        ErrorResponse error = new ErrorResponse();
        error.setMessage("Unknow ERROR");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR );


    }
    
}
