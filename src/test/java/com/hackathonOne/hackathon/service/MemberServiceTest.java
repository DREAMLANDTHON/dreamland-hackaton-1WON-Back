package com.hackathonOne.hackathon.service;

import com.hackathonOne.hackathon.domain.entity.Member;
import com.hackathonOne.hackathon.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;


    @Test
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setId(3L);
        member.setName("inhyuk");
        member.setEmail("123123@gmail.com");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }


    @Test
    public void 회원_찾기() throws Exception{

        //given
        Member member = new Member();
        member.setId(3L);
        member.setName("inhyuk");
        member.setEmail("123123@gmail.com");

        Long savedId = memberService.join(member);

        //when
        Member findMember = memberService.findOne(savedId);
        //then

        assertEquals(findMember.getId() , 3L);
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setId(5L);
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
//        memberService.join(member1);
        try{
            memberService.join(member1);
        } catch(IllegalStateException e){
            return;
        }
        //then
        fail("예외가 발생해야 한다.");
    }



}