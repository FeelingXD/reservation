package com.zerobase.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // 로그인 관련
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"해당정보의 유저를 찾을수 없습니다."),

    // 매니저 관련
    NOT_FOUND_SHOP(HttpStatus.BAD_REQUEST,"해당정보의 가게를 찾을수 없습니다."),
    ACCESS_NOT_ALLOWED(HttpStatus.BAD_REQUEST,"허용되지않는 접근입니다."),
    NOT_PARTNER(HttpStatus.BAD_REQUEST,"해당 유저는 파트너 가입이 되어있지않습니다.."),
    ALREADY_JOINED_PARTNER(HttpStatus.BAD_REQUEST,"이미 파트너 회원입니다."),

    RESERVATION_STATUS_NOT_WAITING_APPROVAL(HttpStatus.BAD_REQUEST,"예약대기상태가 아니거나 이미 처리된 예약상태입니다."),
    //기능관련 권한

    //예약
    NOT_FOUND_RESERVATION(HttpStatus.BAD_REQUEST,"해당 예약을 찾을수없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
