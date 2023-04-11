package com.zerobase.reservation.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class KioskInputForm {
    @ApiModelProperty(notes = "사용자 핸드폰", example = "010-0000-000(String)")
    String customer_phone;
}
