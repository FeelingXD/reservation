package com.zerobase.reservation.model.dto;

import com.zerobase.reservation.model.entity.Shop;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

public class ShopDto {

    @Data
    @Builder
    public static class Simple {
        @ApiModelProperty(notes = "가게 아이디", example = "Longtype number")

        Long id;
        @ApiModelProperty(notes = "가게이름", example = "가게이름")

        String shop_name;
        @ApiModelProperty(notes = "평점", example = "1.0~5.0")

        Double rate;

    }

    @Data
    @Builder
    public static class Detail {
        @ApiModelProperty(notes = "가게 아이디", example = "Longtype number")
        Long id;
        @ApiModelProperty(notes = "가게이름", example = "가게이름")

        String shop_name;
        @ApiModelProperty(notes = "평점", example = "1.0~5.0")

        Double rate;
        @ApiModelProperty(notes = "가게 전화번호", example = "010-0000-000(String)")

        String shop_phone;
        @ApiModelProperty(notes = "가게 주소", example = "경기도 고양시~")

        String shop_location;
    }

    public static Simple toSimple(Shop s) {
        return Simple.builder()
                .id(s.getId())
                .shop_name(s.getName())
                .rate(s.getRate())
                .build();
    }

    public static Detail toDetail(Shop s) {
        return Detail.builder()
                .id(s.getId())
                .shop_name(s.getName())
                .rate(s.getRate())
                .shop_location(s.getLocation())
                .shop_phone(s.getPhone())
                .build();
    }
}
