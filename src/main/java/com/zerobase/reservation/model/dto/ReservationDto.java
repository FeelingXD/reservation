package com.zerobase.reservation.model.dto;

import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.constant.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@Builder
public class ReservationDto {
    String reservateAt;

    public static Reservation toEntity(ReservationDto dto){
        return Reservation
                .builder()
                .reservationAt(LocalDateTime.parse(dto.reservateAt, DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")))
                .reservationStatus(ReservationStatus.WAITING_FOR_APPROVAL)
                .build();
    }
}
