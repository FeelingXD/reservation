package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.jwt.JwtAuthenticationProvider;
import com.zerobase.reservation.model.UserVo;
import com.zerobase.reservation.model.entity.Reservation;
import com.zerobase.reservation.model.entity.Review;
import com.zerobase.reservation.model.entity.constraint.ReservationStatus;
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

    public Review writeReview(String token, Long reservation_id, ReviewInputForm form) { //리뷰 작성

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

        return reviewRepository.save(review);
    }
}
