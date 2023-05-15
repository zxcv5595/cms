package com.zxcv5595.cms.order.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final ErrorCode errorCode;
    private final int status;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
        this.status = errorCode.getStatusCode().value();
    }

}