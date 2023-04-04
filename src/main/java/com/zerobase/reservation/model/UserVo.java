package com.zerobase.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class UserVo {
    private Long customer_id;
    private String email;
}
