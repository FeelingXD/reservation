package com.zerobase.reservation.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvise {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<CustomException.CustomExceptionResponse> customExceptionResponseEntity(
      CustomException e) {// Customer Exception heandler
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(CustomException.CustomExceptionResponse.builder()
            .message(e.getMessage())
            .code(e.getErrorCode().toString())
            .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class) //@Validate exception handler
  public ResponseEntity<CustomValidationException.Response> handleValidationException(
      MethodArgumentNotValidException e) {
    BindingResult result = e.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();
    String errorMessage = fieldErrors.stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.joining(","));

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(CustomValidationException.Response.builder()
            .message(errorMessage)
            .build());
  }
}
