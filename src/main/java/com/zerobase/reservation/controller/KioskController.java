package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.form.KioskInputForm;
import com.zerobase.reservation.service.KioskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kiosk")
public class KioskController {
    private final String TOKEN_HEADER = "X-AUTH-TOKEN";

    private final KioskService kioskService;
    
    @PostMapping("/add/{shop_id}")
    public String addKiosk(@RequestHeader(name = TOKEN_HEADER) String token,@PathVariable Long shop_id ){ //키오스크 추가
        //todo : token => manager  해당 shop_id의 관리자와 로그인한 매니저의 권한을 확인후 해당 상점에 키오스크를 추가
        kioskService.addKiosk(token,shop_id);
        return "키오스크 추가완료.";
    }
    @PostMapping("/check/{kiosk_id}")
    public String checkReservation(@RequestHeader(name = TOKEN_HEADER) String token,@PathVariable Long kiosk_id, @RequestBody KioskInputForm form){
        kioskService.checkReservation(kiosk_id,form);
        return "사용가능합니다.";
    }
}
