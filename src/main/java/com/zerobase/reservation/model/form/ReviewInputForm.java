package com.zerobase.reservation.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReviewInputForm {
    @ApiModelProperty(notes = "제목" , example = "subject")

    private String subject;
    @ApiModelProperty(notes = "본문" , example = "text")

    private String text;
    @ApiModelProperty(notes = "점수" , example = "1.0(1.0~5.0)")

    private Double rate;
}
