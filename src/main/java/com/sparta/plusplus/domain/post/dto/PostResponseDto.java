package com.sparta.plusplus.domain.post.dto;

import com.sparta.plusplus.domain.comment.dto.*;
import com.sparta.plusplus.domain.post.*;
import java.time.*;
import java.util.*;
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
    private Long heartCnt;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList;

    public static PostResponseDto formingWith(Post post) {
        return PostResponseDto.builder()
            .id(post.getId())
            .title(post.getTitle())
            .username(post.getUser().getUsername())
            .content(post.getContent())
            .heartCnt(post.getHeartCnt())
            .createdAt(post.getCreatedAt())
            .build();
    }

    public static PostResponseDto formingWith(Post post,
        List<CommentResponseDto> responseDtoList) {
        return PostResponseDto.builder()
            .id(post.getId())
            .title(post.getTitle())
            .username(post.getUser().getUsername())
            .content(post.getContent())
            .heartCnt(post.getHeartCnt())
            .createdAt(post.getCreatedAt())
            .commentList(responseDtoList)
            .build();
    }
}
