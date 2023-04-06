package com.zerobase.reservation.controller;

import antlr.Token;
import com.zerobase.reservation.model.dto.ShopDto;
import com.zerobase.reservation.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/shop")
@RequiredArgsConstructor
public class ShopController {

    private final String TOKEN_HEADER="X-AUTH-TOKEN";

    private final ManagerService managerService;

    @PostMapping("/add")
    public String addShop(@RequestHeader(name = TOKEN_HEADER) String token, ShopDto dto){
        managerService.addShop(token,dto);
        return "추가 되었습니다.";
    }
    @DeleteMapping("/delete/{shop_id}")
    public ResponseEntity<String> deleteShop(@RequestHeader(name = TOKEN_HEADER) String token,@PathVariable Long shop_id){
        managerService.deleteShop(token,shop_id);
        return ResponseEntity.ok("삭제되었습니다.");
    }

}
