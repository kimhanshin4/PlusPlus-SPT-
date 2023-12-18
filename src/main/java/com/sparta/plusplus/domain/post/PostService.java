package com.sparta.plusplus.domain.post;

import static com.sparta.plusplus.global.exception.ResultCode.NOT_EXIST_POST;

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
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post newPost = Post.builder()
            .title(requestDto.getTitle())
            .user(user)
            .content(requestDto.getContent())
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

        return postList.stream().map(post -> PostListResponseDto.formingWith(post, page))
            .collect(Collectors.toList());
    }

    public PostResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new GlobalException(NOT_EXIST_POST));
        return PostResponseDto.formingWith(post);
    }
}
