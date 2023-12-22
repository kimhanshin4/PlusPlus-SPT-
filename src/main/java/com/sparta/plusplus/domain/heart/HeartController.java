package com.sparta.plusplus.domain.heart;

import com.sparta.plusplus.domain.heart.dto.*;
import com.sparta.plusplus.global.security.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/hearts")
public class HeartController {

    private final HeartService heartService;

    @PutMapping
    public ResponseEntity<HeartResponseDto> giveHeart(@PathVariable Long postId,
        @AuthenticationPrincipal
        UserDetailsImpl userDetails) {
        HeartResponseDto responseDto = heartService.giveHeart(postId, userDetails.getUser());
        return ResponseEntity.ok().body(responseDto);
    }
}
