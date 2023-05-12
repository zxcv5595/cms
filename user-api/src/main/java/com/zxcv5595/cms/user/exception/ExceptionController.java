package com.zxcv5595.cms.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public ErrorResponse customExceptionHandler(CustomException e) {
        log.error("'{}':'{}'", e.getErrorCode(), e.getDetail());

        return new ErrorResponse(e.getErrorCode(), e.getStatusCode(), e.getDetail());
    }


}
