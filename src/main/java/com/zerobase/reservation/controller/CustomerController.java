package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.repository.CustomerRepository;
import com.zerobase.reservation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/signup")// 회원가입
    public ResponseEntity customerSignUp(SignUpForm form){
        customerService.signUp(form);

        return ResponseEntity.ok().body("가입이완료되었습니다.");
    }

    @PostMapping("/signin")//  로그인
    public ResponseEntity customerSignIn(){

        return ResponseEntity.ok().build();
    }
}
