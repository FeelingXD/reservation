package com.zerobase.reservation.model.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReviewInputForm {
    private String subject;
    private String text;
    private Double rate;
}
