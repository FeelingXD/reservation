package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.form.KioskInputForm;
import com.zerobase.reservation.service.KioskService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kiosk")
public class KioskController {

  private final String TOKEN_HEADER = "X-AUTH-TOKEN";

  private final KioskService kioskService;

  @ApiOperation(value = "상점 키오스크추가"
      , notes = "토큰으로 아이디 유효성검사를하고 해당 가게에 키오스크를 추가함")
  @PostMapping("/add/{shopId}")
  public String addKiosk(@RequestHeader(name = TOKEN_HEADER) String token,
      @PathVariable Long shopId) { //키오스크 추가
    kioskService.addKiosk(token, shopId);
    return "키오스크 추가완료.";
  }

  @ApiOperation(value = "고객 키오스크 사용"
      , notes = "키오스크 아이디로 어떤매장인지 확인하고 해당매장에 고객 번호로 예약이 되어있다면 해당예약 사용")
  @PostMapping("/check/{kioskId}")
  public String checkReservation(@PathVariable Long kioskId,
      @Validated @RequestBody KioskInputForm form) { // 손님 예약확인 (키오스크 업무)
    kioskService.checkReservation(kioskId, form);
    return "사용가능합니다.";
  }
}
