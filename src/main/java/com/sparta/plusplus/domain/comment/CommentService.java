package com.sparta.plusplus.domain.comment;

import static com.sparta.plusplus.global.exception.ResultCode.*;

import com.sparta.plusplus.domain.comment.dto.*;
import com.sparta.plusplus.domain.post.*;
import com.sparta.plusplus.domain.user.*;
import com.sparta.plusplus.global.exception.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    @Transactional
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

    @Transactional
    public CommentResponseDto modifyComment(Long postId, Long commentId,
        CommentRequestDto requestDto, User user) {
        postService.findPost(postId);
        Comment comment = findComment(commentId);
        checkIdentification(user, comment);
        comment.updateComment(requestDto);
        return CommentResponseDto.builder().id(commentId).username(comment.getUser().getUsername())
            .content(requestDto.getContent()).modifiedAt(comment.getModifiedAt()).build();
    }

    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new GlobalException(NOT_EXIST_COMMENT));
    }

    private static void checkIdentification(User user, Comment comment) {
        if (!comment.getUser().getUsername().equals(user.getUsername())) {
            throw new GlobalException(NOT_EXIST_COMMENT);
        }
    }
}
