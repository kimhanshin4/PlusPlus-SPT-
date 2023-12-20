package com.sparta.plusplus.domain.comment;

import com.sparta.plusplus.domain.comment.dto.*;
import com.sparta.plusplus.domain.post.*;
import com.sparta.plusplus.domain.user.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, User user) {
        Post post = postService.findPost(postId);
        Comment newComment = Comment.builder()
            .post(post)
            .user(user)
            .content(requestDto.getContent())
            .build();
        commentRepository.save(newComment);
        return CommentResponseDto.formWith(newComment);
    }
}
