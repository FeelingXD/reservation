package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.jwt.JwtAuthenticationProvider;
import com.zerobase.reservation.model.UserVo;
import com.zerobase.reservation.model.entity.Manager;
import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.Shop;
import com.zerobase.reservation.model.entity.constant.ReservationStatus;
import com.zerobase.reservation.model.entity.constant.UserType;
import com.zerobase.reservation.model.form.ShopInputForm;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.repository.ManagerRepository;
import com.zerobase.reservation.repository.ReservationRepository;
import com.zerobase.reservation.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private static final UserType TYPE = UserType.MANAGER;

    private final ManagerRepository managerRepository;
    private final ShopRepository shopRepository;
    private final ReservationRepository reservationRepository;
    private final JwtAuthenticationProvider provider;


    public Manager signUp(SignUpForm form) {// 가입
        return managerRepository.save(Manager.from(form));
    }

    public String signIn(SignInForm form) { //로그인
        //return token
        Manager manager = validateSignIn(form).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));//로그인정보없음
        return provider.createToken(manager.getEmail(), manager.getId(), TYPE);
    }

    public Manager joinPartner(String token) { // 매니저 아이디  파트너 가입
        UserVo user = provider.getUserVo(token);
        Manager m = managerRepository.findByIdAndEmail(user.getId(), user.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (m.isPartner()) { //이미 파트너인 경우
            throw new CustomException(ErrorCode.ALREADY_JOINED_PARTNER);
        }
        m.setPartner(true);
        return managerRepository.save(m);
    }
    //Shop

    public Shop addShop(String token, ShopInputForm dto) {
        UserVo user = provider.getUserVo(token);
        Manager m = managerRepository.findByIdAndEmail(user.getId(), user.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (!m.isPartner()) {
            throw new CustomException(ErrorCode.NOT_PARTNER);
        }

        Shop s = ShopInputForm.toEntity(dto);
        s.setManager(m);

        return shopRepository.save(s);
    }

    public void deleteShop(String token, Long id) {
        UserVo user = provider.getUserVo(token);
        Manager m = managerRepository.findByIdAndEmail(user.getId(), user.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        Shop s = shopRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOP));

        if (Objects.equals(s.getManager().getId(), user.getId())) {
            shopRepository.deleteById(id);
        } else {
            throw new CustomException(ErrorCode.ACCESS_NOT_ALLOWED);
        }
    }

    public Reservation approveReservation(String token, Long reservationId) {

        UserVo user = provider.getUserVo(token);
        Manager m = managerRepository.findByIdAndEmail(user.getId(), user.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Reservation r = reservationRepository.findById(reservationId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        if(!Objects.equals(r.getShop().getManager().getId(), user.getId()))
            throw new CustomException(ErrorCode.ACCESS_NOT_ALLOWED);
        if (r.getReservationStatus() != ReservationStatus.WAITING_FOR_APPROVAL)
            throw new CustomException(ErrorCode.RESERVATION_STATUS_NOT_WAITING_APPROVAL);

        r.setReservationStatus(ReservationStatus.RESERVATION_COMPLETE);
        return reservationRepository.save(r);
    }

    //아이디(이메일),비밀번호로 로그인 가능여부판단하기)
    public Reservation rejectReservation(String token, Long reservationId) {
        UserVo user = provider.getUserVo(token);
        Manager m = managerRepository.findByIdAndEmail(user.getId(), user.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Reservation r= reservationRepository.findById(reservationId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));

        if(Objects.equals(r.getShop().getManager().getId(), user.getId()))
            throw new CustomException(ErrorCode.ACCESS_NOT_ALLOWED);
        if (r.getReservationStatus()!=ReservationStatus.WAITING_FOR_APPROVAL)
            throw new CustomException(ErrorCode.RESERVATION_STATUS_NOT_WAITING_APPROVAL);

        r.setReservationStatus(ReservationStatus.RESERVATION_REJECTED);
        return reservationRepository.save(r);
    }

    private Optional<Manager> validateSignIn(SignInForm form) {
        return managerRepository.findByEmailAndPassword(form.getEmail(), form.getPassword());
    }

}