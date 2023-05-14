package com.zxcv5595.cms.user.application;

import com.zxcv5595.cms.user.domain.SignInForm;
import com.zxcv5595.cms.user.domain.model.Customer;
import com.zxcv5595.cms.user.domain.model.Seller;
import com.zxcv5595.cms.user.exception.CustomException;
import com.zxcv5595.cms.user.exception.ErrorCode;
import com.zxcv5595.cms.user.service.customer.CustomerService;
import com.zxcv5595.cms.user.service.seller.SellerService;
import com.zxcv5595.domain.config.JwtAuthenticationProvider;
import com.zxcv5595.domain.domain.common.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final SellerService sellerService;
    public String customerLoginToken(SignInForm form) {
            Customer customer = customerService.findValidCustomer(form.getEmail(),
                            form.getPassword())
                    .orElseThrow(() -> new CustomException(
                            ErrorCode.LOGIN_CHECK_FAIL));
            return jwtAuthenticationProvider.createToken(customer.getEmail(), customer.getId(),
                    UserType.CUSTOMER);

    }

    public String sellerLoginToken(SignInForm form) {
        Seller seller = sellerService.findValidSeller(form.getEmail(),
                        form.getPassword());
        return jwtAuthenticationProvider.createToken(seller.getEmail(), seller.getId(),
                UserType.SELLER);

    }
}
