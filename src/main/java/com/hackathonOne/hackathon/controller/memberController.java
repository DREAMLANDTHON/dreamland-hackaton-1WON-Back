package com.hackathonOne.hackathon.controller;

import com.hackathonOne.hackathon.domain.entity.Member;
import com.hackathonOne.hackathon.domain.request.AdditionalSignUpRequest;
import com.hackathonOne.hackathon.domain.request.CreateMemberFirstRequest;
import com.hackathonOne.hackathon.domain.request.UpdateMemberRequest;
import com.hackathonOne.hackathon.domain.response.AdditionalSignUpResponse;
import com.hackathonOne.hackathon.domain.response.CreateMemberFirstResponse;
import com.hackathonOne.hackathon.domain.response.GetProfileResponse;
import com.hackathonOne.hackathon.domain.response.UpdateMemberResponse;
import com.hackathonOne.hackathon.repository.CanEatRepository;
import com.hackathonOne.hackathon.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class memberController {

    private final MemberService memberService;
    private final CanEatRepository canEatRepository;

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

//    @PutMapping("/api/user/{id}")
//    public UpdateMemberResponse updateMember(@PathVariable("id")Long id,
//                                             @RequestBody @Valid UpdateMemberRequest request){
//        memberService.updateProfile(id, )
//    }

    @GetMapping("/api/user/{id}")
    public GetProfileResponse getProfile(@PathVariable("id")Long id) {
        Member findMember = memberService.findOne(id);

//        return new GetProfileResponse(new UpdateMemberRequest.MemberDto(findMember.getName()))
//        return findMember;

        List<GetProfileResponse.AllergyDto> allergyDtos = findMember.getAllergies().stream().map(o -> new GetProfileResponse.AllergyDto(o.getId(), o.getName())).collect(Collectors.toList());
        List<GetProfileResponse.SpecialTypeDto> specialTypeDtos = findMember.getSpecialTypes().stream().map(o -> new GetProfileResponse.SpecialTypeDto(o.getId(), o.getName())).collect(Collectors.toList());
        List<GetProfileResponse.CanEatDto> canEatDtos = findMember.getCanEats().stream().map(o ->
        {
            int size = canEatRepository.findAllByName(o.getName()).size();
            return new GetProfileResponse.CanEatDto(o.getId(), o.getName(),size);
        }
        ).collect(Collectors.toList());
        return new GetProfileResponse(new GetProfileResponse.MemberDto(findMember.getName()), allergyDtos, specialTypeDtos, canEatDtos);
    }


}
