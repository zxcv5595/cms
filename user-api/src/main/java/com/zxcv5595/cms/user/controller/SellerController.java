package com.zxcv5595.cms.user.controller;

import com.zxcv5595.cms.user.domain.LoginDto;
import com.zxcv5595.cms.user.domain.model.Seller;
import com.zxcv5595.cms.user.service.seller.SellerService;
import com.zxcv5595.domain.config.JwtAuthenticationProvider;
import com.zxcv5595.domain.domain.common.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @GetMapping("/getInfo")
    public ResponseEntity<LoginDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo userVo = jwtAuthenticationProvider.getUserVo(token);
        Seller seller = sellerService.findByIdAndEmail(userVo.getId(), userVo.getEmail());

        return ResponseEntity.ok(LoginDto.from(seller));
    }
}
