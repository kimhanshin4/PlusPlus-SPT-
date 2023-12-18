package com.sparta.plusplus.domain.user.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {

    private String name;
    private String text;
}
