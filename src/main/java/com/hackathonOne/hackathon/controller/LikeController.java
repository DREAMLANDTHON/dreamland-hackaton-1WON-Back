package com.hackathonOne.hackathon.controller;

import com.hackathonOne.hackathon.domain.request.LikeRequest;
import com.hackathonOne.hackathon.domain.response.LikeResponse;
import com.hackathonOne.hackathon.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final MemberService memberService;

    /**
     * 북마크 생성
     */

    @PostMapping("/api/item/{user_id}/like")
    public LikeResponse addLike(@PathVariable("user_id") Long userId, @RequestBody @Valid LikeRequest request) {
        memberService.updateLike(userId, request.getName());

        return new LikeResponse(userId);
    }

    @DeleteMapping("/api/item/{user_id}/like")
    public LikeResponse deleteLike(@PathVariable("user_id") Long userId, @RequestBody @Valid LikeRequest request) {
        memberService.deleteLike(userId, request.getName());

        return new LikeResponse(userId);
    }


}
