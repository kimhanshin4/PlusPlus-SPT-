package com.sparta.plusplus.domain.post;

import com.sparta.plusplus.domain.comment.*;
import com.sparta.plusplus.domain.post.dto.*;
import com.sparta.plusplus.domain.user.*;
import com.sparta.plusplus.global.util.*;
import jakarta.persistence.*;
import java.util.*;
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
//    private boolean isLiked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //User와 양방향
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;
    //TODO 좋아요
//    public void postLike() {
//        this.isLiked = !isLiked;
//    }

    public void modifyPost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
