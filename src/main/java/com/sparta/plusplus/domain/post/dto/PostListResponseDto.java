package com.sparta.plusplus.domain.post.dto;

import com.sparta.plusplus.domain.post.*;
import jakarta.persistence.*;
import java.time.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class PostListResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private Integer page;

    public static PostListResponseDto formingWith(Post newPost, int page) {
        return PostListResponseDto.builder()
            .id(newPost.getId())
            .title(newPost.getTitle())
            .username(newPost.getUser().getUsername())
            .content(newPost.getContent())
            .createdAt(newPost.getCreatedAt())
            .page(page + 1)
            .build();
    }
}
