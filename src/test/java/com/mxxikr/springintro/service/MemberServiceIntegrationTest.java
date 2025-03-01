package com.mxxikr.springintro.service;

import com.mxxikr.springintro.domain.Member;
import com.mxxikr.springintro.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행
@Transactional // 테스트 케이스에 이 어노테이션이 있으면 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /**
     * 회원가입 테스트
     */
    @Test
//    @Commit // 테스트 완료 후에도 롤백하지 않고 커밋
    public void joinTest() throws Exception {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long serviceId = memberService.join(member);

        // then
        Member findMember = memberRepository.findById(serviceId).get();
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
    }
}
