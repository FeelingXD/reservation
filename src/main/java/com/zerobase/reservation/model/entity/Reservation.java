package com.zerobase.reservation.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zerobase.reservation.model.entity.constant.ReservationStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer; //고객

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop; //상점

    @OneToOne(mappedBy = "reservation")
    private Review review;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private LocalDateTime reservationAt;


}
