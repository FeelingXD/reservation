package com.zerobase.reservation.model.dto;

import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.constraint.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDto {
    private Long reservationId;
    private String shop_name;

    private String customer_phone;

    private LocalDateTime reservatedAt;
    private ReservationStatus reservationStatus;

    public static ReservationDto toDto(Reservation r) {
        return ReservationDto.builder()
                .reservatedAt(r.getReservationAt())
                .reservationStatus(r.getReservationStatus())
                .reservationId(r.getId())
                .customer_phone(r.getCustomer().getPhone())
                .shop_name(r.getShop().getName())
                .build();
    }
}
