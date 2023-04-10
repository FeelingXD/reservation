package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.form.KioskInputForm;
import com.zerobase.reservation.service.KioskService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kiosk")
public class KioskController {
    private final String TOKEN_HEADER = "X-AUTH-TOKEN";

    private final KioskService kioskService;
    @ApiOperation(value = "상점 키오스크추가"
            ,notes ="토큰으로 아이디 유효성검사를하고 해당 가게에 키오스크를 추가함" )
    @PostMapping("/add/{shop_id}")
    public String addKiosk(@RequestHeader(name = TOKEN_HEADER) String token, @PathVariable Long shop_id) { //키오스크 추가
        //todo : token => manager  해당 shop_id의 관리자와 로그인한 매니저의 권한을 확인후 해당 상점에 키오스크를 추가
        kioskService.addKiosk(token, shop_id);
        return "키오스크 추가완료.";
    }

    @ApiOperation(value = "고객 키오스크 사용"
            ,notes ="키오스크 아이디로 어떤매장인지 확인하고 해당매장에 고객 번호로 예약이 되어있다면 해당예약 사용" )
    @PostMapping("/check/{kiosk_id}")
    public String checkReservation(@RequestHeader(name = TOKEN_HEADER) String token, @PathVariable Long kiosk_id, @RequestBody KioskInputForm form) { // 손님 예약확인 (키오스크 업무)
        kioskService.checkReservation(kiosk_id, form);
        return "사용가능합니다.";
    }
}
