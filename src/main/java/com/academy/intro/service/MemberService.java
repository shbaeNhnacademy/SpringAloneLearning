package com.academy.intro.service;

import com.academy.intro.domain.Member;
import com.academy.intro.repository.MemberRepository;
import com.academy.intro.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *  회원가입.
     * @param member 입력받는 멤버.
     * @return 멤버 id.
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });

    }

    /**
     *  멤버 전체 찾기.
     * @return 멤버 리스트.
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     *  멤버 한명 찾기.
     * @param id 찾고자하는 멤버의 id.
     * @return 해당하는 id의 멤버.
     */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
