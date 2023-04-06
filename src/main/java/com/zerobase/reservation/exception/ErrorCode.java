package com.zerobase.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // 로그인 관련
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"해당정보의 유저를 찾을수 없습니다."),
    ALREADY_JOINED_PARTNER(HttpStatus.BAD_REQUEST,"이미 파트너 회원입니다. ");

    
    //기능관련 권한
    NOT_FOUND_SHOP(HttpStatus.BAD_REQUEST,"이미 파트너 회원입니다. ");

    private final HttpStatus httpStatus;
    private final String detail;
}
