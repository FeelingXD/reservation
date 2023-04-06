package com.zerobase.reservation.jwt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class JwtAuthenticationProviderTest {

    @Autowired
    private JwtAuthenticationProvider provider;


    @Test
    void checkToken() {
        //given
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJZMDFyRk41c2dkb3JXL0pjQmhnMkxqbXRlWHJGU0owUmE2Y3RGRStWaTBzPSIsImp0aSI6InFpalNBakR1VUZRVnlabDFkMStsZ1E9PSIsInJvbGVzIjoiQ1VTVE9NRVIiLCJpYXQiOjE2ODA2MDU5MjQsImV4cCI6MTY4MDY5MjMyNH0.5FM_lv27vPGLfsAaeywLTA1z-0vvV2P6U_mH67X27jU";
        //when
        System.out.println(provider.getUserVo(token).getEmail()+provider.getUserVo(token).getId());
        //then
    }


}