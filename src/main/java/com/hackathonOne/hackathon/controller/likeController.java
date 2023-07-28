package com.hackathonOne.hackathon.controller;

import com.hackathonOne.hackathon.domain.request.LikeRequest;
import com.hackathonOne.hackathon.domain.response.LikeResponse;
import com.hackathonOne.hackathon.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class likeController {

    private final MemberService memberService;

    /**
     * 북마크 생성
     */

    @PostMapping("/api/item/{user_id}/like")
    public LikeResponse addLike(@PathVariable("user_id")Long user_id, @RequestBody @Valid LikeRequest request){
        memberService.updateLike(user_id, request.getName());

        return new LikeResponse(user_id);
    }
}
