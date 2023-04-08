package com.zerobase.reservation.model.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class KioskInputForm {
    Long id;
    String customer_phone;
}
