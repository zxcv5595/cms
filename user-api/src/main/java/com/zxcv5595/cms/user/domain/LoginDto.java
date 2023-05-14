package com.zxcv5595.cms.user.domain;

import com.zxcv5595.cms.user.domain.model.Customer;
import com.zxcv5595.cms.user.domain.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {

    private Long id;
    private String email;

    public static LoginDto from(Customer customer) {
        return new LoginDto(customer.getId(), customer.getEmail());
    }

    public static LoginDto from(Seller seller) {
        return new LoginDto(seller.getId(),seller.getEmail());
    }

}
