package com.zerobase.reservation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zerobase.reservation.model.form.ReservationInputForm;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final String TOKEN_HEADER = "X-AUTH-TOKEN";

    private final CustomerService customerService;

    @PostMapping("/signup")// 회원가입
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form) {
        customerService.signUp(form);
        return ResponseEntity.ok().body("가입이완료되었습니다.");
    }

    @PostMapping("/signin")//  로그인
    public ResponseEntity<String> customerSignIn(@RequestBody SignInForm form) {

        return ResponseEntity.ok(customerService.signIn(form)); //return login token
    }


    @PostMapping("/reservation/{shop_id}")
    public ResponseEntity<String> reservateShop(@RequestHeader(name = TOKEN_HEADER) String token, @PathVariable(name = "shop_id") Long shop_id, @RequestBody ReservationInputForm dto) {
        customerService.reservateShop(token, shop_id, dto);
        return ResponseEntity.ok().body("예약 되었습니다.");
    }

    //본인 예약확인하기
    @GetMapping("/reservation/")
    public ResponseEntity<List<String>> getMyReservation(@RequestHeader(name = TOKEN_HEADER) String token) throws JsonProcessingException {
        var result = customerService.getMyReservation(token);
        return ResponseEntity.ok().body(result);
    }

}
