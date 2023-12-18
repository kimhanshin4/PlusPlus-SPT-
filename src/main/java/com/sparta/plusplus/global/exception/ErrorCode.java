package com.sparta.plusplus.global.exception;

import org.springframework.http.*;

public interface ErrorCode {

    String name();

    HttpStatus getHttpStatus();

    String getMessage();
}
