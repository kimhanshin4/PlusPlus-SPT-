package com.sparta.plusplus.global.common;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponseDto {

    private String msg;
    private Integer statusCode;
}
