package com.sparta.plusplus.global.common;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponseDto {

    private String msg;
    private Integer statusCode;
}
