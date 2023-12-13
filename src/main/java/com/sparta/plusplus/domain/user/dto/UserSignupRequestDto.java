package com.sparta.plusplus.domain.user.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
public class UserSignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{4,10}$", message = "영소대문자와 숫자 중 4~10글자 에요!")
    private String username;
    @NotBlank
    @Size(min = 2, max = 15, message = "4~10 글자 에요!")
    private String nickname;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]{8,30}$", message = "영소대문자,숫자,특수문자 조합의 8~30글자 에요!")
    private String password;
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
