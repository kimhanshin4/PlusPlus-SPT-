package com.sparta.plusplus.domain.user;

import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    boolean existsByNickname(String inputNickname);

}
