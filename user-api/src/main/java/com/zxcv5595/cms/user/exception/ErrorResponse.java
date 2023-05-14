package com.zxcv5595.cms.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final ErrorCode errorCode;
    private final int statusCode;
    private final String errorMessage;


    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.statusCode = errorCode.getStatusCode().value();
        this.errorMessage = errorCode.getDetail();
    }
}
