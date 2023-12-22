package com.sparta.plusplus.domain.heart;

import com.sparta.plusplus.domain.post.*;
import com.sparta.plusplus.domain.user.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByUserAndPost(User user, Post post);

}
