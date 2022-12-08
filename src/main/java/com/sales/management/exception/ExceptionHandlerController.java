package com.sales.management.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleAccessDeniedException(Exception ex) {
        return new ResponseEntity<>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
