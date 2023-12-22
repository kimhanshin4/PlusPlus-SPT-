package com.sparta.plusplus.domain.heart.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartResponseDto {

    private Boolean isHearted;

    public static HeartResponseDto formWith(Boolean isHearted) {
        return HeartResponseDto.builder()
            .isHearted(isHearted)
            .build();
    }
}
