package com.zerobase.reservation.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="manger_id")
    private Manager manager;

    @Column(name = "shop_name")
    private String name;

    private String phone;
    private String location;

}
