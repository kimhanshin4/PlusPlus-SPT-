package com.sparta.plusplus.domain.user;

import com.sparta.plusplus.domain.user.dto.*;
import com.sparta.plusplus.global.common.*;
import com.sparta.plusplus.global.response.*;
import com.sparta.plusplus.global.security.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import java.util.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public RestResponse<SignupResponseDto> signup(
        @Valid @RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return RestResponse.success(
            SignupResponseDto.builder().name("sign-up").text("일원이 된걸 축하하오!").build());
    }

    @PostMapping("/signup/nicknames")
    public RestResponse<NicknameCheckResponseDto> checkNickname(
        @Valid @RequestBody NicknameCheckRequestDto requestDto) {
        return RestResponse.success(userService.checkExistNickname(requestDto));
    }
//    public ResponseEntity<NicknameCheckResponseDto> checkNickname(
//        @Valid @RequestBody NicknameCheckRequestDto requestDto) {
//        return ResponseEntity.status(200).body(userService.checkExistNickname(requestDto));
//    }

    @PostMapping("/login")
    public RestResponse<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
//        try {
        userService.login(requestDto);
        return RestResponse.success(
            LoginResponseDto.builder().name("log-in").text("좋아, 통과일세!").build());
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest()
//                .body(new CommonResponseDto("음? 무언가 틀렸구먼. 다시 확인 해보게!",
//                    HttpStatus.BAD_REQUEST.value()));
//        }
    }

}
