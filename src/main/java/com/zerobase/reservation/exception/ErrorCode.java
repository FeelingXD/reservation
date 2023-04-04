package com.zerobase.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"해당정보의 유저를 찾을수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
