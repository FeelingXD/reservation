package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class CustomerServiceTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void deleteReservation() { //당일 일경우

        //given
        reservationRepository.save(Reservation.builder()
                .reservationAt(LocalDateTime.parse("11.11.11 11:11", DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")))
                .build());
        //when
        Reservation r = reservationRepository.findById(1L).orElseThrow();
        //then
        System.out.println(r.getReservationAt());
        if (r.getReservationAt().truncatedTo(ChronoUnit.DAYS).equals(LocalDateTime.parse("11.11.11 13:36", DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")).truncatedTo(ChronoUnit.DAYS))) {
            throw new CustomException(ErrorCode.SAME_DAY_CANCELLATION);
        }


    }

    @Test
    void nowTime() {
        System.out.println("\n\n\n\n" + LocalDateTime.now());
    }


}