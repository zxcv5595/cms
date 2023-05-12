package com.zxcv5595.cms.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
        ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST,"이미 가입한 회원입니다."),
        ALREADY_VERIFY(HttpStatus.BAD_REQUEST,"이미 인증이 완료되었습니다."),
        EXPIRED_CODE(HttpStatus.BAD_REQUEST,"인증시간이 만료 되었습니다."),
        WRONG_VERIFICATION(HttpStatus.BAD_REQUEST,"잘못된 인증 시도입니다."),
        NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"일치하는 회원이 없습니다.");

        private final HttpStatus statusCode;
        private final String detail;


}