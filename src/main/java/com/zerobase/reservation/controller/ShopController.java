package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.dto.ShopDto;
import com.zerobase.reservation.model.form.ShopInputForm;
import com.zerobase.reservation.service.ManagerService;
import com.zerobase.reservation.service.ShopService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

  private final String TOKEN_HEADER = "X-AUTH-TOKEN";
  private final ManagerService managerService;
  private final ShopService shopService;

  @ApiOperation(value = "상점 조회"
      , notes = "모든상점을 조회함")
  @GetMapping
  public ResponseEntity<List<ShopDto.Simple>> getShop() {
    return ResponseEntity.ok().body(shopService.getAllShop());
  }

  @ApiOperation(value = "상점 조회(타입)"
      , notes = "해당 타입으로 정렬된순서로 상점을조회함(RATE,NAME) ")
  @GetMapping("/{sortType}")
  public ResponseEntity<List<ShopDto.Simple>> getSortedShop(@PathVariable String sortType) {
    return ResponseEntity.ok().body(shopService.getSortedShop(sortType));
  }

  @ApiOperation(value = "상점 조회(상세)"
      , notes = "해당 상점아이디로 상점의 상세정보를 조회 ")
  @GetMapping("/detail/{shopId}")
  public ResponseEntity<ShopDto.Detail> getDetail(@PathVariable Long shopId) {
    return ResponseEntity.ok().body(shopService.getDetail(shopId));
  }

  @ApiOperation(value = "상점 추가"
      , notes = "매니저 토큰으로 유효성검사 상점의 추가 ")
  @PostMapping("/add")
  public ResponseEntity<String> addShop(@RequestHeader(name = TOKEN_HEADER) String token,
      @Validated @RequestBody ShopInputForm form) { //매니저 상점 추가
    managerService.addShop(token, form);
    return ResponseEntity.ok().body("추가 되었습니다.");
  }

  @ApiOperation(value = "상점 삭제"
      , notes = "매니저 토큰으로 유효성검사 상점 삭제")
  @DeleteMapping("/delete/{shopId}")
  public ResponseEntity<String> deleteShop(@RequestHeader(name = TOKEN_HEADER) String token,
      @PathVariable Long shopId) {//매니저 상점 삭제
    managerService.deleteShop(token, shopId);
    return ResponseEntity.ok().body("삭제되었습니다.");
  }

}
