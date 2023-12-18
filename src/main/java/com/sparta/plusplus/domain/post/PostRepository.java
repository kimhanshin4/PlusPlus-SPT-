package com.sparta.plusplus.domain.post;

import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedAtDesc();

}
