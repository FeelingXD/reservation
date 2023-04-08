package com.zerobase.reservation.model.entity.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ReservationStatusTest {

    private ReservationStatus reservationStatus;

    @BeforeEach
    @Test
    void testValueOfmethod(){
        //given
        String text="RESERVATION_REJECTED";
        //when
        System.out.println(ReservationStatus.valueOf(text));
        assertEquals(ReservationStatus.valueOfText(text),ReservationStatus.RESERVATION_REJECTED);
        //then
    }
}