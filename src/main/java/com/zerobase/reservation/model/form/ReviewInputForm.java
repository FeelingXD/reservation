package com.zerobase.reservation.model.form;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
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
public class ReviewInputForm {

  @ApiModelProperty(notes = "제목", example = "subject")
  @NotNull
  private String subject;
  @ApiModelProperty(notes = "본문", example = "text")
  @NotNull
  private String text;
  @ApiModelProperty(notes = "점수", example = "1.0(1.0~5.0)")
  @DecimalMin(value = "1.0", inclusive = true, message = "Value must be greater than or equal to 0.0")
  @DecimalMax(value = "5.0", inclusive = true, message = "Value must be smaller than or equal to 0.0")
  @NotNull
  private Double rate;
}
