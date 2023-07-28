package com.hackathonOne.hackathon.controller;

import com.hackathonOne.hackathon.domain.entity.Member;
import com.hackathonOne.hackathon.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class memberController {

    private final MemberService memberService;

    @PostMapping("/api/user/first")
    public CreateMemberFirstResponse saveMemberFirst(@RequestBody @Valid CreateMemberFirstRequest request){
        Member member = new Member();
        member.setName(request.getName());
        member.setId(request.getId());
        member.setEmail(request.getEmail());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }
}
