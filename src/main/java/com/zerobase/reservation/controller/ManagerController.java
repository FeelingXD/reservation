package com.zerobase.reservation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

  private final String TOKEN_HEADER = "X-AUTH-TOKEN";
  private final ManagerService managerService;

  @ApiOperation(value = "매니저 회원가입"
      , notes = "회원가입 폼으로 회원가입을함")
  @PostMapping("/signup")// 회원가입
  public ResponseEntity<String> managerSignUp(@Validated @RequestBody SignUpForm form) {
    managerService.signUp(form);
    return ResponseEntity.ok().body("가입이완료되었습니다.");
  }

  @ApiOperation(value = "매니저 로그인"
      , notes = "이메일과 비밀번호로 로그인하며 토큰을 반환")
  @PostMapping("/signin")//  로그인
  public ResponseEntity<String> managerSignIn(@Validated @RequestBody SignInForm form) {
    return ResponseEntity.ok(managerService.signIn(form)); //return login token
  }

  @ApiOperation(value = "매니저 파트너 가입"
      , notes = "별다른 제약조건은 없으며 토큰으로 유효성검사를 통해 해당매니저에게 파트너권한을줌 ")
  @PostMapping("/joinpartner") //파트너쉽 가입
  public ResponseEntity<String> joinPartnerShip(@RequestHeader(name = TOKEN_HEADER) String token) {
    managerService.joinPartner(token);
    return ResponseEntity.ok().body("가입이완료되었습니다.");
  }

  @ApiOperation(value = "매니저 예약 조회"
      , notes = "매니저 예약 조회 모든 값을 조회 ")
  @GetMapping("/reservation")// 매니저가 처리할수있는 모든예약확인
  public ResponseEntity<List<String>> getReservation(
      @RequestHeader(name = TOKEN_HEADER) String token) throws JsonProcessingException {
    var result = managerService.getReservation(token);
    return ResponseEntity.ok().body(result);
  }

  @ApiOperation(value = "매니저 예약 조회"
      , notes = "매니저 예약조회 {reservationStatus} 의 상태값에 해당하는 예약만 조회함 ")
  @GetMapping("/reservation/{reservationStatus}")
// 매니저가 처리할수있는 모든예약확인 reservationStatus 값에해당하는 예약만 확인가능
  public ResponseEntity<List<String>> getReservation(
      @RequestHeader(name = TOKEN_HEADER) String token, @PathVariable String reservationStatus)
      throws JsonProcessingException {
    var result = managerService.getReservation(token, reservationStatus);
    return ResponseEntity.ok().body(result);
  }

  @ApiOperation(value = "매니저 예약 승인"
      , notes = "매니저 권한으로 해당 예약번호의 예약을 승인함")
  @PatchMapping("/reservation/approve/{reservation_id}") //예약 승인
  public ResponseEntity<String> approveReservation(@RequestHeader(name = TOKEN_HEADER) String token,
      @PathVariable Long reservation_id) {
    managerService.approveReservation(token, reservation_id);
    return ResponseEntity.ok().body("승인되었습니다.");
  }

  @ApiOperation(value = "매니저 예약 거절"
      , notes = "매니저 권한으로 해당 예약번호의 예약을 거절함")
  @PatchMapping("/reservation/reject/{reservation_id}") //예약 거절
  public ResponseEntity<String> rejectReservation(@RequestHeader(name = TOKEN_HEADER) String token,
      @PathVariable Long reservation_id) {
    managerService.rejectReservation(token, reservation_id);
    return ResponseEntity.ok().body("거절되었습니다..");
  }

}
