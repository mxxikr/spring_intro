package com.mxxikr.springintro.service;

import com.mxxikr.springintro.domain.Member;
import com.mxxikr.springintro.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /**
     * @BeforeEach : 각 테스트가 실행되기 전에 실행
     * 같은 저장소를 사용하기 위해 MemberService에 MemoryMemberRepository를 주입
     */
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository(); // MemoryMemberRepository 객체 생성
        memberService = new MemberService(memberRepository); // MemberService 객체 생성
    }

    /**
     * @AfterEach : 각 테스트가 끝날 때마다 실행
     */
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    /**
     * 회원가입 테스트
     */
    @Test
    public void joinTest() throws Exception {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long serviceId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(serviceId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    /**
     * 중복 회원 예외 테스트
     */
    @Test
    public void duplicateMemberTest() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // 예외가 발생해야함

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail(); // 예외가 발생해야함
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }
}
