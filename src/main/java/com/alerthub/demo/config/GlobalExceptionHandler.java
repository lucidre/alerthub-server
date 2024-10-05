package com.alerthub.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.alerthub.demo.NetworkResult;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<NetworkResult>handleBadRequest(Exception ex, WebRequest request) {
        return new ResponseEntity<>( new NetworkResult(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<NetworkResult> handleInternalServerError(Exception ex, WebRequest request) {
        return new ResponseEntity<>( new NetworkResult(ex.getMessage(), null),  HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
