package com.zerobase.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // 로그인 관련
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "해당정보의 유저를 찾을수 없습니다."),
    WRONG_VARIABLE(HttpStatus.BAD_REQUEST, "잘못된 요청값 입니다."),
    //입력폼 관련
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "올바르지 않는 입력값 입니다. "),

    // 매니저 관련
    NOT_FOUND_SHOP(HttpStatus.BAD_REQUEST, "해당정보의 가게를 찾을수 없습니다."),
    ACCESS_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "허용되지않는 접근입니다."),
    INVALID_REVIEW_WRITE(HttpStatus.BAD_REQUEST, "허용되지않는 접근입니다.(로그인 TOKEN 혹은 해당 예약아이디가 없습니다."),
    NOT_PARTNER(HttpStatus.BAD_REQUEST, "해당 유저는 파트너 가입이 되어있지않습니다.."),
    ALREADY_JOINED_PARTNER(HttpStatus.BAD_REQUEST, "이미 파트너 회원입니다."),
    //예약 상태
    RESERVATION_STATUS_NOT_WAITING_APPROVAL(HttpStatus.BAD_REQUEST, "예약대기상태가 아니거나 이미 처리된 예약상태입니다."),
    RESERVATION_STATUS_NOT_RESERVATION_COMPLETE(HttpStatus.BAD_REQUEST, "예약상태가 아닙니다."),
    RESERVATION_STATUS_NOT_USE_COMPLETE(HttpStatus.BAD_REQUEST, "해당 예약이 사용완료상태가 아닙니다. 사용완료후 작성해주세요"),
    RESERVATION_TIME_OVER(HttpStatus.BAD_REQUEST, "예약시간 오버로 사용이 불가능합니다."),
    //review 관련
    INVALID_FORM_RATE(HttpStatus.BAD_REQUEST, "1.0부터 5.0점까지 가능합니다."),

    //예약
    NOT_FOUND_KIOSK(HttpStatus.BAD_REQUEST, "해당정보의 키오스크를 찾을수 없습니다."),

    NOT_FOUND_RESERVATION(HttpStatus.BAD_REQUEST, "해당 예약을 찾을수없습니다."),
    KIOSK_UNMATCHED_SHOP(HttpStatus.BAD_REQUEST, "키오스크정보와 예약상점간 정보가 일치하지않습니다. "),

    SAME_DAY_CANCELLATION(HttpStatus.BAD_REQUEST, "당일 취소는 불가능합니다. "),
    RESERVATION_NOT_SAME_DAY(HttpStatus.BAD_REQUEST, "예약 일자가 다릅니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
