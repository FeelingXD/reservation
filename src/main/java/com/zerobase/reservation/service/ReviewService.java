package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.jwt.JwtAuthenticationProvider;
import com.zerobase.reservation.model.UserVo;
import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.Review;
import com.zerobase.reservation.model.entity.constant.ReservationStatus;
import com.zerobase.reservation.model.form.ReviewInputForm;
import com.zerobase.reservation.repository.ReservationRepository;
import com.zerobase.reservation.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final JwtAuthenticationProvider provider;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;

    public void writeReview(String token, Long reservation_id, ReviewInputForm form) {
        /*
         * todo: token 검사
         *  reservation 가져온후 reservation status=> use_complete 인지확인 사용해야 리뷰작성가능
         *  리뷰당 한건만 작성가능하기에 reservation id 마다 one to one으로 작성
         *  form 으로는 제목 내용 점수를 받음
         *  reservation 의 customer 의 이메일 아이디와 token 으로 유효성 검사 //=> 작성 가능여부 판별
         *  reservation 의 status 검사
         *  작성이후 reservation 의 status 를 review로 바꿀것
         *
         * */

        UserVo user = provider.getUserVo(token);
        Reservation r = new ArrayList<>(reservationRepository.findByIdAndCustomer_Email(reservation_id, user.getEmail())).get(0);

        if (r == null) {
            throw new CustomException(ErrorCode.INVALID_REVIEW_WRITE);
        } else if (r.getReservationStatus() != ReservationStatus.USE_COMPLETE) {
            throw new CustomException(ErrorCode.RESERVATION_STATUS_NOT_USE_COMPLETE);
        } else if (!(1.0 <= form.getRate() && form.getRate() <= 5.0)) {
            throw new CustomException(ErrorCode.INVALID_FORM_RATE);
        }

        var review = Review.builder()
                .subject(form.getSubject())
                .text(form.getText())
                .rate(form.getRate())
                .reservation(r)
                .shop(r.getShop())
                .build();

        reviewRepository.save(review);
    }
}
