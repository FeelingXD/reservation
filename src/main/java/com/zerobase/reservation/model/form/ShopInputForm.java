package com.zerobase.reservation.model.form;

import com.zerobase.reservation.model.entity.Shop;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class ShopInputForm {
    private String name;
    private String phone;
    private String location;

    public static Shop toEntity(ShopInputForm dto){
        return Shop.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .location(dto.getLocation())
                .build();
    }
}