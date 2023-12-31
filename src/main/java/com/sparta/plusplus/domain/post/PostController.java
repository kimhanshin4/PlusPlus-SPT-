package com.sparta.plusplus.domain.post;

import com.sparta.plusplus.domain.post.dto.*;
import com.sparta.plusplus.global.security.*;
import jakarta.validation.*;
import java.util.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto requestDto,
        @AuthenticationPrincipal
        UserDetailsImpl userDetails) {
        PostResponseDto responseDto = postService.createPost(requestDto, userDetails.getUser());
        return ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostListResponseDto>> getPostList(
        @RequestParam("page") int page,
        @RequestParam("size") int size,
        @RequestParam("sortBy") String sortBy,
        @RequestParam("isAsc") boolean isAsc) {
        List<PostListResponseDto> responseDtoList = postService.getPostList(page - 1, size, sortBy,
            isAsc);
        return ResponseEntity.ok().body(responseDtoList);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto responseDto = postService.getPost(postId);
        return ResponseEntity.status(200).body(responseDto);
    }

    @PatchMapping("/{postId}/modify")
    public ResponseEntity<PostResponseDto> modifyPost(@PathVariable Long postId, @Valid
    @RequestBody PostRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto responseDto = postService.modifyPost(postId, requestDto,
            userDetails.getUser());
        return ResponseEntity.status(200).body(responseDto);
    }

    @DeleteMapping("/{postId}/delete")
    public void deletePost(@PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails.getUser());
    }
}
