package com.sparta.plusplus.global.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public class RestApiException {

    private String errorMessage;
    private int statusCode;


}
