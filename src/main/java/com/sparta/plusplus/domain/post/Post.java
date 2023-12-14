package com.sparta.plusplus.domain.post;

import com.sparta.plusplus.domain.user.*;
import com.sparta.plusplus.global.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.*;
import jdk.jshell.execution.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private boolean isLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //TODO 좋아요
    public void postLike() {
        this.isLiked = !isLiked;
    }
}
