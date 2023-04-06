package com.zerobase.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvise {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomException.CustomExceptionResponse> customExceptionResponseEntity(CustomException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CustomException.CustomExceptionResponse.builder()
                        .message(e.getMessage())
                        .code(e.getErrorCode().toString())
                        .build());
    }
}