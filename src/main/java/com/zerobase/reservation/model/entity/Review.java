package com.zerobase.reservation.model.entity;

import com.zerobase.reservation.model.entity.constraint.ReservationStatus;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Reservation reservation;
  @ManyToOne
  private Shop shop;

  private String subject;
  private String text;
  private Double rate;

  @PostPersist
  @PostUpdate
  public void updateShopRate() {
    if (reservation != null && reservation.getShop() != null) {
      Shop s = reservation.getShop();
      List<Review> reviews = shop.getReview();
      Double totalRate = 0.0;

      for (Review review : reviews) {
        totalRate += review.getRate();
      }
      reservation.setReservationStatus(ReservationStatus.REVIEW_COMPLETE);
      shop.setRate(totalRate / reviews.size());
    }
  }
}
