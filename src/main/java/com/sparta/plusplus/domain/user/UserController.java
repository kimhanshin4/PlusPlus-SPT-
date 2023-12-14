package com.sparta.plusplus.domain.user;

import com.sparta.plusplus.domain.user.dto.*;
import com.sparta.plusplus.global.common.*;
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
    public ResponseEntity<CommonResponseDto> signup(
        @Valid @RequestBody UserSignupRequestDto requestDto,
        BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest()
                .body(new CommonResponseDto("저런, 우린 함께 할 수 없네!", HttpStatus.BAD_REQUEST.value()));
        }
        userService.signup(requestDto);
        return ResponseEntity.status(201)
            .body(new CommonResponseDto("일원이 된걸 축하하오!", HttpStatus.CREATED.value()));
    }

    @PostMapping("/signup/nicknames")
    public ResponseEntity<NicknameCheckResponseDto> checkNickname(
        @Valid @RequestBody NicknameCheckRequestDto requestDto) {
        return ResponseEntity.status(200).body(userService.checkExistNickname(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        try {
            userService.login(requestDto);
            return ResponseEntity.ok()
                .body(new CommonResponseDto("어서 들어오게나!", HttpStatus.OK.value()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDto("음? 무언가 틀렸구먼. 다시 확인 해보게!",
                    HttpStatus.BAD_REQUEST.value()));
        }
    }

}
