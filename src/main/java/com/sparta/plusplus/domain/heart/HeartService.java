package com.sparta.plusplus.domain.heart;

import static com.sparta.plusplus.domain.heart.dto.HeartConstant.*;

import com.sparta.plusplus.domain.heart.dto.*;
import com.sparta.plusplus.domain.post.*;
import com.sparta.plusplus.domain.user.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final PostService postService;

    @Transactional
    public HeartResponseDto giveHeart(User user, Long postId) {
        Post post = postService.findPost(postId);
        Heart heart = heartRepository.findByUserAndPost(user, post).orElseGet(
            () -> firstHeart(user, post));

        boolean heartStatus = heart.updateHeart();
        post.stackHeart(heartStatus);

        return HeartResponseDto.formWith(heart.getIsHearted());
    }

    private Heart firstHeart(User user, Post post) {
        Heart heart = Heart.builder()
            .isHearted(DEFAULT_HEART)
            .user(user)
            .post(post)
            .build();

        return heartRepository.save(heart);
    }

}
