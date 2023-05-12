package com.zxcv5595.cms.user.service;

import com.zxcv5595.cms.user.domain.SignUpForm;
import com.zxcv5595.cms.user.domain.model.Customer;
import com.zxcv5595.cms.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm signUpForm) {
        return customerRepository.save(Customer.from(signUpForm));
    }

}
