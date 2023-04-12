package com.zerobase.reservation.model.form;

import io.swagger.annotations.ApiModelProperty;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReviewInputForm {
    @ApiModelProperty(notes = "제목" , example = "subject")
    @NotNull
    private String subject;
    @ApiModelProperty(notes = "본문" , example = "text")
    @NotNull
    private String text;
    @ApiModelProperty(notes = "점수" , example = "1.0(1.0~5.0)")
    @DecimalMin(value = "1.0",inclusive = true, message = "Value must be greater than or equal to 0.0")
    @DecimalMax(value = "5.0",inclusive = true, message = "Value must be greater than or equal to 0.0")
    @NotBlank
    private Double rate;
}
