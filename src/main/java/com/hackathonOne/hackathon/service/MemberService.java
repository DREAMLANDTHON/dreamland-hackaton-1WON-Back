package com.hackathonOne.hackathon.service;

import com.hackathonOne.hackathon.domain.entity.Allergy;
import com.hackathonOne.hackathon.domain.entity.CanEat;
import com.hackathonOne.hackathon.domain.entity.Member;
import com.hackathonOne.hackathon.domain.entity.SpecialType;
import com.hackathonOne.hackathon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

//    public void update(Long id , String name , ) {
//        Member member = memberRepository.findOne(id);
//        member.setName(name);
//    }

    @Transactional
    public void updateMemberHealthInfo(Long id , List<String> allergies, List<String> specialTypes) {
        Member member = memberRepository.findOne(id);

        List<Allergy> allergtList = new ArrayList<>();

        for(String s : allergies){
            Allergy allergy = new Allergy();
            allergy.setName(s);
//            allergy.setMember(member);
//            allergtList.add(allergy);

            member.addAllergy(allergy);
        }

        List<SpecialType> spcialTypeList = new ArrayList<>();

        for(String s : specialTypes){
            SpecialType specialType = new SpecialType();
            specialType.setName(s);
//            specialType.setMember(member);
//            spcialTypeList.add(specialType);
            member.addSpecialType(specialType);
        }

//
//        member.setAllergies(allergtList);
//        member.setSpecialTypes(spcialTypeList);
    }

    @Transactional
    public void updateLike(Long user_id , String item_name){
        Member findMember = memberRepository.findOne(user_id);

        CanEat canEat = new CanEat();
        canEat.setName(item_name);
//        findMember.getCanEats().stream().map(o -> {if (o.getName() == item_name) return ; });
        boolean exists = findMember.getCanEats().stream()
                .anyMatch(o -> o.getName().equals(item_name));
        if(!exists) findMember.addCanEat(canEat);

    }
}
