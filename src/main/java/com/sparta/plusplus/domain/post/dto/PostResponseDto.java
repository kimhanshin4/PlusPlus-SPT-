package com.sparta.plusplus.domain.post.dto;

import com.sparta.plusplus.domain.post.*;
import java.time.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDto {

    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public static PostResponseDto formingWith(Post newPost) {
        return PostResponseDto.builder()
            .id(newPost.getId())
            .title(newPost.getTitle())
            .username(newPost.getUser().getUsername())
            .content(newPost.getContent())
            .createdAt(newPost.getCreatedAt())
            .build();
    }
}
