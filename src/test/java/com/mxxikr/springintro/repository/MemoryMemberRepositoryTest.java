package com.mxxikr.springintro.repository;

import com.mxxikr.springintro.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


public class MemoryMemberRepositoryTest {
    // MemoryMemberRepository 객체 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * @AfterEach: 각 테스트가 끝날 때마다 실행
     * 각 테스트가 끝날 때마다 저장소 초기화
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 저장소 초기화
    }

    /**
     * @Test: 테스트 메서드
     * assertThat: 검증 메서드
     *
     * 회원 저장 테스트
     */
    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring"); // spring 이름을 가진 회원 객체 생성

        // when
        repository.save(member); // 회원 저장

        // then
        Member result = repository.findById(member.getId()).get(); // 저장된 회원을 id로 찾아 반환
        assertThat(result).isEqualTo(member); // 저장된 회원과 member가 같은지 확인
    }

    /**
     * 회원 이름으로 찾기 테스트
     */
    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get(); // 이름으로 찾아 반환

        // then
        assertThat(result).isEqualTo(member1);
    }

    /**
     * 모든 회원 찾기 테스트
     */
    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll(); // 저장된 모든 회원 반환

        // then
        assertThat(result.size()).isEqualTo(2); // 저장된 회원 수가 2명인지 확인
    }
}
