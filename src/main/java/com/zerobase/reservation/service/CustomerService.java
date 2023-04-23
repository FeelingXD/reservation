package com.zerobase.reservation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.jwt.JwtAuthenticationProvider;
import com.zerobase.reservation.model.UserVo;
import com.zerobase.reservation.model.dto.ReservationDto;
import com.zerobase.reservation.model.entity.Customer;
import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.Shop;
import com.zerobase.reservation.model.entity.constraint.ReservationStatus;
import com.zerobase.reservation.model.entity.constraint.UserType;
import com.zerobase.reservation.model.form.ReservationInputForm;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.repository.CustomerRepository;
import com.zerobase.reservation.repository.ReservationRepository;
import com.zerobase.reservation.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ShopRepository shopRepository;
    private final ReservationRepository reservationRepository;

    private final ObjectMapper mapper;
    private final JwtAuthenticationProvider provider;
    private static final UserType TYPE = UserType.CUSTOMER;

    public Customer signUp(SignUpForm form) {// 가입
        return customerRepository.save(Customer.from(form));
    }

    public String signIn(SignInForm form) { //로그인
        //return token
        Customer customer = validateSignIn(form).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));//로그인정보없음
        return provider.createToken(customer.getEmail(), customer.getId(), TYPE);
    }

    public Reservation makeReservation(String token, Long shop_id, ReservationInputForm dto) { //shopId 를 통해 reservation 생성
        UserVo user = provider.getUserVo(token);
        Customer c = customerRepository.findByIdAndEmail(user.getId(), user.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Shop s = shopRepository.findById(shop_id).stream().findFirst().orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOP));

        reservationRepository.findByCustomer(c).stream()
                .filter(reservation -> reservation.getShop().equals(s))
                .filter(reservation -> reservation.getReservationAt().truncatedTo(ChronoUnit.DAYS)
                        .equals(LocalDateTime.parse(dto.getReservateAt(), DateTimeFormatter.ofPattern("yy.MM.dd HH:mm")).truncatedTo(ChronoUnit.DAYS)))
                .findAny()
                .ifPresent(reservation -> {
                    throw new CustomException(ErrorCode.ALREADY_HAVE_RESERVATION);
                });
        

        Reservation r = ReservationInputForm.toEntity(dto);

        r.setCustomer(c);
        r.setShop(s);

        return reservationRepository.save(r);

    }

    public List<String> getMyReservation(String token) throws JsonProcessingException {
        UserVo user = provider.getUserVo(token);

        Customer c = customerRepository.findAllById(user.getId())
                .filter(customer -> customer.getEmail().equals(user.getEmail()))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        List<String> result = new ArrayList<>();
        for (Reservation r : reservationRepository.findByCustomer(c)) {
            result.add(mapper.writeValueAsString(ReservationDto.toDto(r)));
        }
        return result;
    }

    //아이디(이메일),비밀번호로 로그인 가능여부판단하기)
    private Optional<Customer> validateSignIn(SignInForm form) {
        return customerRepository.findByEmailAndPassword(form.getEmail(), form.getPassword());
    }


    public void cancelReservation(String token, Long reservation_id) {
        UserVo user = provider.getUserVo(token);
        Customer c = customerRepository.findByIdAndEmail(user.getId(), user.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        Reservation r = reservationRepository.findById(reservation_id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVATION));
        if (r.getReservationAt().truncatedTo(ChronoUnit.DAYS).equals(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS))) {
            throw new CustomException(ErrorCode.SAME_DAY_CANCELLATION);
        }
        r.setReservationStatus(ReservationStatus.CUSTOMER_CANCEL);
        reservationRepository.save(r);
    }
}