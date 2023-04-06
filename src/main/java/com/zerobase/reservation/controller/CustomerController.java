package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.entity.constant.UserType;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final String TOKEN_HEADER="X-AUTH-TOKEN";
    private final CustomerService customerService;
    @PostMapping("/signup")// 회원가입
    public ResponseEntity customerSignUp(@RequestBody SignUpForm form) {
        customerService.signUp(form);
        return ResponseEntity.ok().body("가입이완료되었습니다.");
    }

    @PostMapping("/signin")//  로그인
    public ResponseEntity<String> customerSignIn(@RequestBody SignInForm form) {

        return ResponseEntity.ok(customerService.signIn(form)); //return login token
    }

    //예약하기
    @PostMapping("/reservation/{shop_id}")
    public ResponseEntity makeReservation(
            @RequestHeader(name = TOKEN_HEADER) String token,
            String shop_id) {

        return null;
    }
    //본인예약확인하기
    @GetMapping("/reservation/")
    public ResponseEntity getMyReservation() {
        return null;
    }




}
