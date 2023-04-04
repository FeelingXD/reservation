package com.zerobase.reservation.model.entity.constant;

import lombok.Getter;

@Getter
public enum UserType {
    CUSTOMER("고객"),
    OWNER("점주")
    ;

    private final String type;
    UserType(String type){
        this.type=type;
    }
}