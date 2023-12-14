package com.sparta.plusplus.domain.user.dto;

import com.sparta.plusplus.global.common.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class NicknameCheckResponseDto extends CommonResponseDto {

    private boolean existNickname;

}
