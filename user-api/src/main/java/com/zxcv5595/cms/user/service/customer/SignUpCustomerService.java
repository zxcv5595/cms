package com.zxcv5595.cms.user.service.customer;

import com.zxcv5595.cms.user.domain.SignUpForm;
import com.zxcv5595.cms.user.domain.model.Customer;
import com.zxcv5595.cms.user.domain.repository.CustomerRepository;
import com.zxcv5595.cms.user.exception.CustomException;
import com.zxcv5595.cms.user.exception.ErrorCode;
import java.time.LocalDateTime;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm signUpForm) {
        return customerRepository.save(Customer.from(signUpForm));
    }

    public boolean isEmailExist(String email) {
        email = email.toLowerCase(Locale.ROOT);
        return customerRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code) {
        Customer customer = customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_USER)
                );
        if(customer.isVerify()){
            throw new CustomException(ErrorCode.ALREADY_VERIFY);
        }else if(!customer.getVerificationCode().equals(code)){
            throw new CustomException(ErrorCode.WRONG_VERIFICATION);
        }else if(customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
            throw new CustomException(ErrorCode.EXPIRED_CODE);
        }
        customer.setVerify(true);
    }

    @Transactional
    public LocalDateTime ChangerCustomerValidateEmail(Long customerId, String verificationCode) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );
        customer.setVerificationCode(verificationCode);
        customer.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
        return customer.getVerifyExpiredAt();
    }

}
