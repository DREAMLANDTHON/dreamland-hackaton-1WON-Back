package com.hackathonOne.hackathon.controller;

import com.hackathonOne.hackathon.domain.entity.Member;
import com.hackathonOne.hackathon.domain.request.AdditionalSignUpRequest;
import com.hackathonOne.hackathon.domain.request.CreateMemberFirstRequest;
import com.hackathonOne.hackathon.domain.response.AdditionalSignUpResponse;
import com.hackathonOne.hackathon.domain.response.CreateMemberFirstResponse;
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

    /**
     * 최초 로그인 시 유저 정보 저장
     */
    @PostMapping("/api/user/first")
    public CreateMemberFirstResponse saveMemberFirst(@RequestBody @Valid CreateMemberFirstRequest request){
        Member member = new Member();
        member.setName(request.getName());
        member.setId(request.getId());
        member.setEmail(request.getEmail());

        Long id = memberService.join(member);

        return new CreateMemberFirstResponse(id);
    }

    /**
     * 추가 회원 정보 입력 받기
     */
    @PostMapping("/api/user")
    public AdditionalSignUpResponse additionalSignUp(@RequestBody @Valid AdditionalSignUpRequest request){
        memberService.updateMemberHealthInfo(request.getId() ,request.getAllergies(), request.getSpecialTypes());
        Member newMember = memberService.findOne(request.getId());

        return new AdditionalSignUpResponse(newMember.getId());
    }


}
