package com.academy.intro.service;

import com.academy.intro.domain.Member;
import com.academy.intro.repository.MemberRepository;
import com.academy.intro.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("Bae");

        //when
        Long join = memberService.join(member);

        //then
        Member member1 = memberRepository.findById(join).get();
        Assertions.assertThat(member1.getId()).isEqualTo(member.getId());
        Assertions.assertThat(member1.getName()).isEqualTo(member.getName());
    }

    @Test
    public void join_duplicate() {
        //given
        Member member = new Member();
        member.setName("Bae");
        Member member1 = new Member();
        member1.setName("Bae");

        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member1);
        });


        //then

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }


}
