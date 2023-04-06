package com.zerobase.reservation.model.entity;

import com.zerobase.reservation.model.form.SignUpForm;
import lombok.*;

import javax.persistence.*;
<<<<<<< Updated upstream
import java.util.Collection;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes

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
    @Column(unique = true)
    private String phone;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Collection<Restaurant> restaurant;

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
