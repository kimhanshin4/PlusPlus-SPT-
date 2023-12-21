package com.sparta.plusplus.domain.comment.dto;

import com.sparta.plusplus.domain.comment.*;
import java.time.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentListResponseDto {

    private Long id;
    private String username;
    private String content;
    private LocalDateTime modifiedAt;
    private Integer page;

    public static CommentListResponseDto formWith(Comment comment, int page) {
        return CommentListResponseDto.builder()
            .id(comment.getId())
            .username(comment.getUser().getUsername())
            .content(comment.getContent())
            .modifiedAt(comment.getModifiedAt())
            .page(page + 1)
            .build();
    }

}
