package com.sparta.plusplus.domain.comment.dto;

import com.sparta.plusplus.domain.comment.*;
import java.time.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {

    private Long id;
    private String username;
    private String content;
    private LocalDateTime modifiedAt;

    public static CommentResponseDto formWith(Comment comment) {
        return CommentResponseDto.builder()
            .id(comment.getId())
            .username(comment.getUser().getUsername())
            .content(comment.getContent())
            .modifiedAt(comment.getModifiedAt())
            .build();
    }

}
