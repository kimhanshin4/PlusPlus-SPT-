package com.sparta.plusplus.global.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public class RestApiException {

    private String errorMSG;
    private int statusCode;


}
