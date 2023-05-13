package com.zerobase.reservation.model.form;

import com.zerobase.reservation.model.entity.Shop;
import io.swagger.annotations.ApiModelProperty;
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
public class ShopInputForm {

  @NotNull
  @ApiModelProperty(notes = "가게이름", example = "임시가게")
  private String name;
  @ApiModelProperty(notes = "가게 전화번호", example = "010-0000-000(String)")
  @NotNull

  private String phone;
  @ApiModelProperty(notes = "상점 주소", example = "경기도 고양시 ~~")
  @NotNull
  private String location;

  public static Shop toEntity(ShopInputForm dto) {
    return Shop.builder()
        .name(dto.getName())
        .phone(dto.getPhone())
        .location(dto.getLocation())
        .rate(0.0)
        .build();
  }
}
