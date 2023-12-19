package com.sparta.plusplus.domain.user;

import static com.sparta.plusplus.global.exception.ResultCode.*;

import com.sparta.plusplus.domain.user.dto.*;
import com.sparta.plusplus.global.exception.*;
import java.util.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ADMIN_TOKEN = "I_AM_SPARTAN";

    //회원가입
    @Transactional
    public void signup(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String encordedPassword = passwordEncoder.encode(password);

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new GlobalException(EXIST_USER);
        }

        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw new GlobalException(EXIST_NICKNAME);
        }

        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new GlobalException(EXIST_EMAIL);
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new GlobalException(JWT_CLAIMS_IS_EMPTY);
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, nickname, encordedPassword, email, role);
        userRepository.save(user);
    }

    //닉네임 중복확인
    @Transactional
    public NicknameCheckResponseDto checkExistNickname(NicknameCheckRequestDto requestDto) {
        return new NicknameCheckResponseDto(
            userRepository.existsByNickname(requestDto.getNickname()));
    }

    public void login(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new GlobalException(NOT_EXIST_USER));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new GlobalException(NOT_AUTHORIZATION);
        }
    }
}
