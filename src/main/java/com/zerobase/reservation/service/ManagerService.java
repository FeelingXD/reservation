package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.jwt.JwtAuthenticationProvider;
import com.zerobase.reservation.model.entity.Manager;
import com.zerobase.reservation.model.entity.constant.UserType;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final JwtAuthenticationProvider provider;

    public Manager signUp(SignUpForm form) {// 가입


        return managerRepository.save(Manager.from(form));
    }

    public String signIn(SignInForm form,UserType type) { //로그인
        //return token
        Manager manager = validateSignIn(form).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));//로그인정보없음
        return provider.createToken(manager.getEmail(), manager.getId(), type);
    }

    //아이디(이메일),비밀번호로 로그인 가능여부판단하기)
    private Optional<Manager> validateSignIn(SignInForm form) {

        return managerRepository.findByEmailAndPassword(form.getEmail(), form.getPassword());
    }


}