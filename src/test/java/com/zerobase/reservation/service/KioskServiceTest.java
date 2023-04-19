package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KioskServiceTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void isSameDay(){
        reservationRepository.save(Reservation.builder()
                .reservationAt(LocalDateTime.parse("11.11.11 11:11", DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")))
                .build());
        //given

        var test=LocalDateTime.of(2011,11,11,0,30).truncatedTo(ChronoUnit.DAYS);
        var r= reservationRepository.findById(1L).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));
        //when
        //then
        assertEquals(test,r.getReservationAt().truncatedTo(ChronoUnit.DAYS));
    }

}