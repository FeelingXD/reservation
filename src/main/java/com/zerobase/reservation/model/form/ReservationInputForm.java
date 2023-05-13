package com.zerobase.reservation.model.form;

import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.constraint.ReservationStatus;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ReservationInputForm {

  @ApiModelProperty(notes = "예약시간 ", example = "yy.MM.dd HH:mm")
  @NotNull
  String reservateAt;

  public static Reservation toEntity(ReservationInputForm dto) {
    return Reservation
        .builder()
        .reservationAt(
            LocalDateTime.parse(dto.reservateAt, DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")))
        .reservationStatus(ReservationStatus.WAITING_FOR_APPROVAL)
        .build();
  }
}
