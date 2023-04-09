package com.zerobase.reservation.model.dto;

import com.zerobase.reservation.model.entity.Shop;
import lombok.Builder;
import lombok.Data;

public class ShopDto {

    @Data
    @Builder
    public static class Simple {
        Long id;
        String shop_name;
        Double rate;

    }

    @Data
    @Builder
    public static class Detail {
        Long id;
        String shop_name;
        Double rate;
        String shop_phone;
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
