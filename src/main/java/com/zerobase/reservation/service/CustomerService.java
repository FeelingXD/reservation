package com.zerobase.reservation.service;

import com.zerobase.reservation.model.entity.Customer;
import com.zerobase.reservation.model.form.SignUpForm;
import com.zerobase.reservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form){
        return customerRepository.save(Customer.from(form));
    }


}