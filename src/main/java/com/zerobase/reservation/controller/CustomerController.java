package com.zerobase.reservation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zerobase.reservation.model.form.ReservationInputForm;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.service.CustomerService;
import io.swagger.annotations.ApiOperation;
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
    
    @ApiOperation(value = "고객 회원가입"
            ,notes ="회원가입 폼으로 회원가입을함" )
    @PostMapping("/signup")// 회원가입
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form) {
        customerService.signUp(form);
        return ResponseEntity.ok().body("가입이완료되었습니다.");
    }

    @ApiOperation(value = "고객 로그인"
            ,notes ="이메일과 비밀번호로 로그인" )
    @PostMapping("/signin")//  로그인
    public ResponseEntity<String> customerSignIn(@RequestBody SignInForm form) {

        return ResponseEntity.ok(customerService.signIn(form)); //return login token
    }

    @ApiOperation(value = "고객 예약하기"
            ,notes ="토큰으로 아이디 유효성검사를하고 shop_id 로 해당 가게로 dto 내용으로 예약함(예약시간)" )
    @PostMapping("/reservation/make/{shop_id}")
    public ResponseEntity<String> makeReservation(@RequestHeader(name = TOKEN_HEADER) String token, @PathVariable(name = "shop_id") Long shop_id, @RequestBody ReservationInputForm dto) { // 예약하기
        customerService.makeReservation(token, shop_id, dto);
        return ResponseEntity.ok().body("예약 되었습니다.");
    }
    @ApiOperation(value = "고객 예약삭제"
            ,notes ="토큰으로 아이디 유효성검사를하고 예약취소(당일취소는 불가능함)" )
    @PutMapping("/reservation/delete/{shop_id}")
    public ResponseEntity<String> deleteReservation(@RequestHeader(name = TOKEN_HEADER) String token, @PathVariable(name = "shop_id") Long shop_id, @RequestBody ReservationInputForm dto) { // 예약취소
        customerService.cancelReservation(token, shop_id);
        return ResponseEntity.ok().body("예약 취소되었습니다.");
    }

    
    //본인 예약확인하기
    @ApiOperation(value = "고객 예약하기"
            ,notes ="토큰으로 아이디 유효성검사를하고 본인이 햇던 모든 예약상황을 가져옴" )
    @GetMapping("/reservation")
    public ResponseEntity<List<String>> getMyReservation(@RequestHeader(name = TOKEN_HEADER) String token) throws JsonProcessingException {
        var result = customerService.getMyReservation(token);
        return ResponseEntity.ok().body(result);
    }

}
