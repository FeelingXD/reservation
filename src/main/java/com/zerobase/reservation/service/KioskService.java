package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.jwt.JwtAuthenticationProvider;
import com.zerobase.reservation.model.UserVo;
import com.zerobase.reservation.model.entity.Kiosk;
import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.Shop;
import com.zerobase.reservation.model.entity.constant.ReservationStatus;
import com.zerobase.reservation.model.form.KioskInputForm;
import com.zerobase.reservation.repository.KioskRepository;
import com.zerobase.reservation.repository.ManagerRepository;
import com.zerobase.reservation.repository.ReservationRepository;
import com.zerobase.reservation.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class KioskService {
    private final ReservationRepository reservationRepository;
    private final ManagerRepository managerRepository;
    private final ShopRepository shopRepository;
    private final KioskRepository kioskRepository;
    private final JwtAuthenticationProvider provider;
    public Kiosk addKiosk(String token, Long shopId) { //매니저토큰 필요! 상점 키오스크 추가하기 .
        UserVo user = provider.getUserVo(token);
        Shop s= shopRepository.findById(shopId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOP));

        if (!(Objects.equals(s.getManager().getId(), user.getId()))||!(Objects.equals(s.getManager().getEmail(),user.getEmail()))){
            throw  new CustomException(ErrorCode.ACCESS_NOT_ALLOWED);
        }
        return kioskRepository.save(Kiosk.builder().shop(s).build());
    }
    public Reservation checkReservation(Long kiosk_id, KioskInputForm form){ // 예약자 전화번호로 예약 승인하기
        var k=kioskRepository.findById(kiosk_id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_KIOSK));
        var r=reservationRepository.findByCustomer_Phone(form.getCustomer_phone())
                .stream().filter(reservation -> reservation.getId().equals(form.getId()))
                .findFirst().orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        if(!Objects.equals(r.getShop().getId(), k.getShop().getId())){
            throw new CustomException(ErrorCode.KIOSK_UNMATCHED_SHOP);
        }

        if(r.getReservationStatus()!=ReservationStatus.RESERVATION_COMPLETE){
            throw new CustomException(ErrorCode.RESERVATION_STATUS_NOT_RESERVATION_COMPLETE);
        }

        if(r.getReservationAt().minusMinutes(10).isBefore(LocalDateTime.now())){
            r.setReservationStatus(ReservationStatus.USE_COMPLETE);
            return reservationRepository.save(r);
        }
        else {
            r.setReservationStatus(ReservationStatus.UNAVAILABLE);
            reservationRepository.save(r);
            throw new CustomException(ErrorCode.RESERVATION_TIME_OVER);
        }
    }



}
