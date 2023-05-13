package com.zerobase.reservation.repository;

import com.zerobase.reservation.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
