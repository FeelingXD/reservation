package com.zerobase.reservation.service;

import com.zerobase.reservation.jwt.JwtAuthenticationProvider;
import com.zerobase.reservation.model.entity.Customer;
import com.zerobase.reservation.model.entity.constant.UserType;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.model.form.SignInForm;
import com.zerobase.reservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final JwtAuthenticationProvider provider;
    public Customer signUp(SignUpForm form){
        return customerRepository.save(Customer.from(form));
    }
    public String signIn(SignInForm form){
        //return token
        Customer customer = validateLogin(form).orElseThrow(() -> new RuntimeException("로그인 오류"));//로그인정보없음
        return provider.createToken(customer.getEmail(), customer.getId(), UserType.CUSTOMER);
    }

    private Optional<Customer> validateLogin(SignInForm form){
        return customerRepository.findByEmailAndPassword(form.getEmail(),form.getPassword());
    }


}