package com.zerobase.reservation.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private final ErrorCode errorCode;

  public CustomException(ErrorCode errorCode) {
    super(errorCode.getDetail());
    this.errorCode = errorCode;
  }

  @Data
  @Builder
  public static class CustomExceptionResponse {

    private int status;
    private String code;
    private String message;
  }
}
