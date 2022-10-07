package com.academy.intro.repository;

import com.academy.intro.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("Kim");

        //when
        repository.save(member);

        //then
        Member member1 = repository.findById(member.getId()).get();
        org.assertj.core.api.Assertions.assertThat(member1).isEqualTo(member);
    }

}
