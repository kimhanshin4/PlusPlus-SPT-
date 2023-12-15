package com.sparta.plusplus.domain.post;

import com.sparta.plusplus.domain.post.dto.*;
import com.sparta.plusplus.domain.user.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {

//        if (!userRepository.existsByUser(user)) {
//            throw new IllegalArgumentException("로그인을 하고 작성해야죠!");
//        }
        Post newPost = Post.builder()
            .title(requestDto.getTitle())
            .user(user)
            .content(requestDto.getContent())
            .build();
        postRepository.save(newPost);
        return PostResponseDto.formingWith(newPost);
    }

    public List<PostListResponseDto> getPostList() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        return postList.stream().map(post -> PostListResponseDto.formingWith(post))
            .collect(Collectors.toList());
//        postList.stream().map(PostListResponseDto::formingWith)
//            .collect(Collectors.toList());
    }
}
