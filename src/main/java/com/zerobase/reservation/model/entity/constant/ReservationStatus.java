package com.zerobase.reservation.model.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ReservationStatus {
    WAITING_FOR_APPROVAL("승인 대기"),
    RESERVATION_COMPLETE("예약 완료"),
    RESERVATION_REJECTED("예약 거부됨"),

    USE_COMPLETE("사용 완료"),
    UNAVAILABLE("사용 불가"),
    REVIEW_COMPLETE("리뷰 작성됨");
    private final String text;

    private static final Map<String,ReservationStatus> BY_TEXT=
            Stream.of(values()).collect(Collectors.toMap(ReservationStatus::name,e->e));
    public static ReservationStatus valueOfText(String text){
        return BY_TEXT.get(text);
    }


}
