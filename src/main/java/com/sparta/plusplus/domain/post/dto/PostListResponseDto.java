package com.sparta.plusplus.domain.post.dto;

import com.sparta.plusplus.domain.post.*;
import java.time.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponseDto {

    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private Integer page;

    public static PostListResponseDto formWith(Post newPost, int page) {
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
