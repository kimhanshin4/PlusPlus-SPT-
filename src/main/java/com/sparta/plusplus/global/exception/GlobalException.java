package com.sparta.plusplus.global.exception;

import lombok.*;

@Getter
public class GlobalException extends RuntimeException {

    private ResultCode resultCode;

    public GlobalException(ResultCode resultCode) {
        super(resultCode.getMessage());
    }
}
