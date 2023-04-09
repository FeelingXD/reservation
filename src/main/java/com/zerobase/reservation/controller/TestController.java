package com.zerobase.reservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/test/api/v1")
public class TestController {
    @GetMapping ("/easteregg")
    public ResponseEntity<String>test(){
        return ResponseEntity.ok().body("Looking back, what kind of person was I? I wonder how ever come to this .. i miss the better dayz.");
    }
}
