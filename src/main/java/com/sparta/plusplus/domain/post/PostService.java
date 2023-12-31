package com.sparta.plusplus.domain.post;

import static com.sparta.plusplus.global.exception.ResultCode.*;

import com.sparta.plusplus.domain.comment.*;
import com.sparta.plusplus.domain.comment.dto.*;
import com.sparta.plusplus.domain.post.dto.*;
import com.sparta.plusplus.domain.user.*;
import com.sparta.plusplus.global.exception.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post newPost = Post.builder()
            .title(requestDto.getTitle())
            .user(user)
            .content(requestDto.getContent())
            .heartCnt(0L)
            .build();
        postRepository.save(newPost);
        return PostResponseDto.formingWith(newPost);
    }

    public List<PostListResponseDto> getPostList(int page, int size, String sortBy, boolean isAsc) {
        // 페이징 처리
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Post> postList = postRepository.findAll(pageable);

        return postList.stream().map(post -> PostListResponseDto.formWith(post, page))
            .collect(Collectors.toList());
    }

    public PostResponseDto getPost(Long postId) {
        Post post = findPost(postId);
        List<CommentResponseDto> responseDtoList = getCommentList(post);

        return PostResponseDto.formingWith(post, responseDtoList);
    }

    @Transactional
    public PostResponseDto modifyPost(Long postId, PostRequestDto requestDto, User user) {
        Post post = findPost(postId);
        checkIdentification(user, post);
        post.modifyPost(requestDto);
        return PostResponseDto.formingWith(post);
    }

    public void deletePost(Long postId, User user) {
        Post post = findPost(postId);
        checkIdentification(user, post);
        postRepository.delete(post);
    }

    private static List<CommentResponseDto> getCommentList(Post post) {
        List<CommentResponseDto> responseDtoList = new ArrayList<>();
        List<Comment> commentList = post.getCommentList();
        //최신 댓글 순으로 정렬
        commentList.sort((o1, o2) -> o2.getModifiedAt().compareTo(o1.getModifiedAt()));
        for (Comment comment : commentList) {
            responseDtoList.add(CommentResponseDto.formWith(comment));
        }
        return responseDtoList;
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new GlobalException(NOT_EXIST_POST));
    }

    private static void checkIdentification(User user, Post post) {
        if (!post.getUser().getUsername().equals(user.getUsername())) {
            throw new GlobalException(NOT_EXIST_POST);
        }
    }
}
