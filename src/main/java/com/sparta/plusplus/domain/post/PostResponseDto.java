package com.sparta.plusplus.domain.post;

import jakarta.persistence.*;
import java.time.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class PostResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
