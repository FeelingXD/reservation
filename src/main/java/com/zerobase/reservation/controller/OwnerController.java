package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.entity.constant.UserType;
import com.zerobase.reservation.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;
    private static UserType TYPE=UserType.CUSTOMER;
}
