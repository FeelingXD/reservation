package com.zerobase.reservation.model.dto;

import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.constant.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDto {
    private Long reservationId;
    private String shop_name;

    private String phone;

    private LocalDateTime reservatedAt;
    private ReservationStatus reservationStatus;

}
