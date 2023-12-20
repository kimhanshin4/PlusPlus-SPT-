package com.sparta.plusplus.domain.comment;

import com.sparta.plusplus.domain.post.*;
import com.sparta.plusplus.domain.user.*;
import com.sparta.plusplus.global.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTime {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") //Post와 양방향
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //User로 단방향
    private User user;

}
