package com.sparta.plusplus.domain.comment;

import com.sparta.plusplus.domain.comment.dto.*;
import com.sparta.plusplus.global.security.*;
import jakarta.validation.*;
import java.util.*;
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

    @GetMapping
    public ResponseEntity<List<CommentListResponseDto>> getCommentList(@PathVariable Long postId,
        @RequestParam("page") int page,
        @RequestParam("size") int size,
        @RequestParam("sortBy") String sortBy,
        @RequestParam("isAsc") boolean isAsc) {
        List<CommentListResponseDto> responseDtoList = commentService.getCommentList(postId, page,
            size, sortBy, isAsc);
        return ResponseEntity.ok().body(responseDtoList);
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
