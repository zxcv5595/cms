package com.zxcv5595.cms.user.controller;

import com.zxcv5595.cms.user.domain.customer.ChangeBalanceForm;
import com.zxcv5595.cms.user.domain.customer.CustomerDto;
import com.zxcv5595.cms.user.domain.model.Customer;
import com.zxcv5595.cms.user.exception.CustomException;
import com.zxcv5595.cms.user.exception.ErrorCode;
import com.zxcv5595.cms.user.service.customer.CustomerBalanceService;
import com.zxcv5595.cms.user.service.customer.CustomerService;
import com.zxcv5595.domain.config.JwtAuthenticationProvider;
import com.zxcv5595.domain.domain.common.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomerService customerService;
    private final CustomerBalanceService customerBalanceService;

    @GetMapping("/getInfo")
    public ResponseEntity<CustomerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        Customer customer = customerService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
                .orElseThrow(() -> new CustomException(
                        ErrorCode.NOT_FOUND_USER));
        return ResponseEntity.ok(CustomerDto.from(customer));
    }

    @PostMapping("/balance")
    public ResponseEntity<Integer> changeChangeBalance(
            @RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody ChangeBalanceForm form) {
        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        return ResponseEntity.ok(
                customerBalanceService.changeBalance(userVo.getId(), form).getCurrentMoney());
    }
}
