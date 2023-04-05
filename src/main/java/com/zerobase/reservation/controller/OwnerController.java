package com.zerobase.reservation.controller;

import com.zerobase.reservation.model.entity.constant.UserType;
import com.zerobase.reservation.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final ManagerService managerService;
    private static UserType TYPE=UserType.CUSTOMER;
}
