package com.zerobase.reservation.service;

import com.zerobase.reservation.exception.CustomException;
import com.zerobase.reservation.exception.ErrorCode;
import com.zerobase.reservation.jwt.JwtAuthenticationProvider;
import com.zerobase.reservation.model.entity.Customer;
import com.zerobase.reservation.model.entity.constant.UserType;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final JwtAuthenticationProvider provider;

    public Customer signUp(SignUpForm form) {// 가입


        return customerRepository.save(Customer.from(form));
    }

    public String signIn(SignInForm form) { //로그인
        //return token
        Customer customer = validateSignIn(form).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));//로그인정보없음
        return provider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
    }

    //아이디(이메일),비밀번호로 로그인 가능여부판단하기)
    private Optional<Customer> validateSignIn(SignInForm form) {

        return customerRepository.findByEmailAndPassword(form.getEmail(), form.getPassword());
    }


}