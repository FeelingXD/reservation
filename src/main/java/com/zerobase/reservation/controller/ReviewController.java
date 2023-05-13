package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.form.ReviewInputForm;
import com.zerobase.reservation.service.ReviewService;
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
@RequestMapping("/review")
public class ReviewController {//리뷰 컨트롤러
  private final String TOKEN_HEADER = "X-AUTH-TOKEN";
  private final ReviewService reviewService;

  @ApiOperation(value = "리뷰 작성"
      , notes = "해당 예약 의 예약상태와 토큰으로 유효성검증을하고 리뷰를작성합니다. ")
  @PostMapping("/write/{reservationId}")
  public String writeReview(@RequestHeader(name = TOKEN_HEADER) String token,
      @PathVariable Long reservationId, @Validated @RequestBody ReviewInputForm form) {
    reviewService.writeReview(token, reservationId, form);
    return "작성 되었습니다.";
  }
}
