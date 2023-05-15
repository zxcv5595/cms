package com.zxcv5595.cms.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST,"이미 가입한 회원입니다.");

    private final HttpStatus statusCode;
    private final String detail;


}