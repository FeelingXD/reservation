package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.form.ReviewInputForm;
import com.zerobase.reservation.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {//리뷰 컨트롤러
    private final String TOKEN_HEADER = "X-AUTH-TOKEN";
    private final ReviewService reviewService;

    @PostMapping("/write/{reservation_id}")
    public ResponseEntity writeReview(@RequestHeader(name = TOKEN_HEADER) String token,@PathVariable Long reservation_id,@RequestBody ReviewInputForm form){
        reviewService.writeReview(token,reservation_id,form);
        return null;
    }
}
