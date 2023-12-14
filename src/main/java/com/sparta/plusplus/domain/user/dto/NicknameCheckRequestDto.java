package com.sparta.plusplus.domain.user.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
public class NicknameCheckRequestDto {


    @NotBlank
    @Size(min = 2, max = 15, message = "4~10 글자 에요!")
    private String nickname;

}
