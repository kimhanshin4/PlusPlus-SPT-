package com.sparta.plusplus.domain.comment;

import com.sparta.plusplus.domain.comment.dto.*;
import com.sparta.plusplus.global.security.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId,
        @Valid @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        CommentResponseDto responseDto = commentService.createComment(postId, requestDto,
            userDetails.getUser());
        return ResponseEntity.status(201).body(responseDto);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> modifyComment(@PathVariable Long postId,
        @PathVariable Long commentId,
        @Valid @RequestBody CommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto responseDto = commentService.modifyComment(postId, commentId,
            requestDto,
            userDetails.getUser());
        return ResponseEntity.ok().body(responseDto);
    }

}
