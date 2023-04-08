package com.zerobase.reservation.model.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="manger_id")
    private Manager manager;


    @OneToMany(mappedBy = "shop",fetch = FetchType.LAZY )
    private List<Reservation> reservation= new ArrayList<>();

    @OneToMany(mappedBy = "shop" , fetch =FetchType.LAZY)
    private List<Kiosk> kiosk = new ArrayList<>();


    private String name;

    private String phone;
    private String location;

}
