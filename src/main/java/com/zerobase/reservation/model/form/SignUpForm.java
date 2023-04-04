package com.zerobase.reservation.model.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignUpForm {
    String email;
    String password;
    String phone;

    String name;

}
