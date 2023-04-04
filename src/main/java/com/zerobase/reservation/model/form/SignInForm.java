package com.zerobase.reservation.model.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignInForm {
    String email;
    String password;
}
