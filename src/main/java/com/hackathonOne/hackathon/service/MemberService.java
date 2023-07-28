package com.hackathonOne.hackathon.service;

import com.hackathonOne.hackathon.domain.entity.Member;
import com.hackathonOne.hackathon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

        Member findMember = memberRepository.findOne(member.getId());
        if(findMember != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

//    public void update(Long id , String name) {
//        Member member = memberRepository.findOne(id);
//        member.setName(name);
//    }
}
