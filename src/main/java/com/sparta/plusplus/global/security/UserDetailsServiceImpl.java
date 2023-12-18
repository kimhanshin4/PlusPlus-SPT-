package com.sparta.plusplus.global.security;

import static com.sparta.plusplus.global.exception.ResultCode.NOT_EXIST_USER;

import com.sparta.plusplus.domain.user.User;
import com.sparta.plusplus.domain.user.*;
import com.sparta.plusplus.global.exception.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new GlobalException(NOT_EXIST_USER));

        return new UserDetailsImpl(user);
    }
}