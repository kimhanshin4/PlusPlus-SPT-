package com.sparta.plusplus.domain.user;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {
    USER(Authority.USER),
    ADMIN(Authority.ADMIN);
    private final String authority;

    public static class Authority {

        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

}
