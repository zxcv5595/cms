package com.zxcv5595.cms.user.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
    private final int statusCode;
    private final String detail;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.statusCode=errorCode.getStatusCode().value();
        this.detail=errorCode.getDetail();
    }

}
