package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final String TOKEN_HEADER="X-AUTH-TOKEN";
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
    public ResponseEntity<String>  joinPartnerShip(@RequestHeader(name = TOKEN_HEADER) String token){
        managerService.joinPartner(token);
        return ResponseEntity.ok().body("가입이완료되었습니다.");
    }

    @PostMapping("/reservation/{reservation_id}")
    public ResponseEntity approveReservation(String token,@RequestParam Long reservation_id){

        managerService.approveReservation(token,reservation_id);
        return ResponseEntity.ok().build();
    }

}
