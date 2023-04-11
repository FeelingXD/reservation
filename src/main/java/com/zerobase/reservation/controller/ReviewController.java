package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.form.ReviewInputForm;
import com.zerobase.reservation.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {//리뷰 컨트롤러
    private final String TOKEN_HEADER = "X-AUTH-TOKEN";
    private final ReviewService reviewService;

    @ApiOperation(value = "리뷰 작성"
            , notes = "해당 예약 의 예약상태와 토큰으로 유효성검증을하고 리뷰를작성합니다. ")
    @PostMapping("/write/{reservation_id}")
    public String writeReview(@RequestHeader(name = TOKEN_HEADER) String token, @PathVariable Long reservation_id, @RequestBody ReviewInputForm form) {
        reviewService.writeReview(token, reservation_id, form);
        return "작성 되었습니다.";
    }
}
