package com.zerobase.reservation.model.dto;

import com.zerobase.reservation.model.entity.Shop;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class ShopDto {
    private String name;
    private String phone;
    private String location;

    public static Shop toEntity(ShopDto dto){
        return Shop.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .location(dto.getLocation())
                .build();
    }
}
