package com.sparta.plusplus.domain.post.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
public class PostRequestDto {

    @Size(max = 500, message = "저런~ 500자 이내로 입력하세요!")
    private String title;
    @Size(max = 5000, message = "으아니 할 말이 그리도 많아요! 5000자 이내로 입력하세요!")
    private String content;

}
