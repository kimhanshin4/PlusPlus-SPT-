package com.sparta.plusplus.domain.comment;

import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByOrderByCreatedAtDesc();

    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
