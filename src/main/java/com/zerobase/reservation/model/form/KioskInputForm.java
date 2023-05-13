package com.zerobase.reservation.model.form;

import com.zerobase.reservation.config.validation.Telephone;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class KioskInputForm {

  @ApiModelProperty(notes = "사용자 핸드폰", example = "010-0000-000(String)")
  @Telephone
  String customerPhone;
}
