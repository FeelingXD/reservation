package com.zerobase.reservation.model.entity;

import com.zerobase.reservation.model.form.SignUpForm;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Customer extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;
  private String password;

  private String name;
  @Column(unique = true)
  private String phone;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Reservation> reservation = new ArrayList<>();

  public static Customer from(SignUpForm form) {
    return Customer.builder()
        .email(form.getEmail())
        .password(form.getPassword())
        .phone(form.getPhone())
        .name(form.getName())
        .build();
  }
}
