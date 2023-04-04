package com.zerobase.reservation.model.entity;

import com.zerobase.reservation.model.form.SignUpForm;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    private String name;
    @Column(unique = true)
    private String phone;

    public static Owner from(SignUpForm form) {
        return Owner.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .phone(form.getPhone())
                .name(form.getName())
                .build();
    }
}