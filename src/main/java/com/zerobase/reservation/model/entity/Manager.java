package com.zerobase.reservation.model.entity;

import com.zerobase.reservation.model.form.SignUpForm;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    private String name;
    @Column
    private String phone;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "manager",cascade = CascadeType.ALL)
    private List<Shop> shop= new ArrayList<>();


    private boolean partner;



    public static Manager from(SignUpForm form) {
        return Manager.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .phone(form.getPhone())
                .name(form.getName())
                .partner(false)
                .build();
    }
}
