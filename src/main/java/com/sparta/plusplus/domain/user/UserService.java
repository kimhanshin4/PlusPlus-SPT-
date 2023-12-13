package com.sparta.plusplus.domain.user;

import com.sparta.plusplus.domain.user.dto.*;
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
    private final String ADMIN_TOKEN = "I_AM_SPRTAN";

    /**
     * 회원가입
     *
     * @param requestDto
     */
    public void signup(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String encordedPassword = passwordEncoder.encode(password);

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("이미 있는 사용자 잖아요!");
        }
        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw new IllegalArgumentException("저런~ 누가 이미 사용중 이에요!");
        }

        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 이메일 이에요!");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("장난하나;");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, nickname, encordedPassword, email, role);
        userRepository.save(user);
    }

    /**
     * 닉네임 중복확인
     *
     * @param requestDto
     */
    public void checkNickname(UserSignupRequestDto requestDto) {

    }
}
