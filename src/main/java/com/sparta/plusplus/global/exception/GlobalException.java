package com.sparta.plusplus.global.exception;

import lombok.*;

@Getter
public class GlobalException extends RuntimeException {

    private ResultCode resultCode;
//    private ErrorCode errorCode;
//    private String errorMessage;
//    private int statusCode;

    public GlobalException(ResultCode resultCode) {
        super(resultCode.getMessage());
    }
}
