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

    public static CommentResponseDto formWith(Comment newComment) {
        return CommentResponseDto.builder()
            .id(newComment.getId())
            .username(newComment.getUser().getUsername())
            .content(newComment.getContent())
            .modifiedAt(newComment.getModifiedAt())
            .build();
    }

}
