package com.zerobase.reservation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zerobase.reservation.model.entity.constant.ReservationStatus;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final String TOKEN_HEADER = "X-AUTH-TOKEN";
    private final ManagerService managerService;

    @PostMapping("/signup")// 회원가입
    public ResponseEntity managerSignUp(@RequestBody SignUpForm form) {
        managerService.signUp(form);
        return ResponseEntity.ok().body("가입이완료되었습니다.");
    }

    @PostMapping("/signin")//  로그인
    public ResponseEntity<String> managerSignIn(@RequestBody SignInForm form) {
        return ResponseEntity.ok(managerService.signIn(form)); //return login token
    }

    @PostMapping("/joinpartner") //파트너쉽 가입
    public ResponseEntity<String> joinPartnerShip(@RequestHeader(name = TOKEN_HEADER) String token) {
        managerService.joinPartner(token);
        return ResponseEntity.ok().body("가입이완료되었습니다.");
    }

    @GetMapping("/reservation")// 매니저가 처리할수있는 모든예약확인
    public ResponseEntity<List<String>> getReservation(@RequestHeader(name = TOKEN_HEADER) String token) throws JsonProcessingException {
        var result=managerService.getReservation(token);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/reservation/{reservation_status}")// 매니저가 처리할수있는 모든예약확인
    public ResponseEntity getReservation(@RequestHeader(name = TOKEN_HEADER) String token, String reservationStatus) throws JsonProcessingException {
        var result=managerService.getReservation(token,reservationStatus);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/reservation/approve/{reservation_id}") //예약 승인
    public ResponseEntity approveReservation(@RequestHeader(name = TOKEN_HEADER) String token,@PathVariable Long reservation_id){
        managerService.approveReservation(token,reservation_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reservation/reject/{reservation_id}") //예약 거절
    public ResponseEntity rejectReservation(@RequestHeader(name = TOKEN_HEADER)String token,@PathVariable Long reservation_id){
        managerService.rejectReservation(token,reservation_id);
        return ResponseEntity.ok().build();
    }

}
