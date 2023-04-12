package com.zerobase.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class CustomValidationException extends RuntimeException {

    public CustomValidationException(String message) {
        super(message);
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Response {
        String message;
    }
}
