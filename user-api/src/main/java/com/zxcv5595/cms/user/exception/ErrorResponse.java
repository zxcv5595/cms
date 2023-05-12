package com.zxcv5595.cms.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private ErrorCode errorCode;
    private int statusCode;
    private String errorMessage;

}
