package com.sparta.plusplus.domain.user;

import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

}
