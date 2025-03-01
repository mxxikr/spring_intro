package com.mxxikr.springintro.service;

import com.mxxikr.springintro.domain.Member;
import com.mxxikr.springintro.repository.MemberRepository;
import com.mxxikr.springintro.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// @Service 어노테이션을 사용하면 스프링 컨테이너에 서비스로 등록
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 생성자를 통한 의존성 주입
     * MemoryMemberRepository 객체를 주입
     */
    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) throws SQLException {
//        long start = System.currentTimeMillis(); // 현재 시간을 밀리초로 반환
        try {
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member); // 회원 저장
            return member.getId(); // 회원 id 반환
        } catch (SQLException e) {
            throw new SQLException("회원 가입 중 오류가 발생했습니다.");
//        } finally {
//            long finish = System.currentTimeMillis(); // 현재 시간을 밀리초로 반환
//            long timeMs = finish - start; // 끝난 시간 - 시작 시간 = 걸린 시간
//            System.out.println("join = " + timeMs + "ms");
        }
    }

    /**
     * 중복 회원 검증
     * .ifPresent() : Optional에 값이 있으면 실행
     * .orElseGet() : Optional에 값이 있으면 꺼내고 없으면 뒤에 있는 것을 실행
     * .orElseThrow() : Optional에 값이 있으면 꺼내고 없으면 예외를 던짐
     *
     * IllegalArgumentException : 잘못된 인자를 전달했을 때 발생하는 예외
     */
    public void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
//        long start = System.currentTimeMillis(); // 현재 시간을 밀리초로 반환
//        try {
            return memberRepository.findAll();
//        } finally {
//            long finish = System.currentTimeMillis(); // 현재 시간을 밀리초로 반환
//            long timeMs = finish - start; // 끝난 시간 - 시작 시간 = 걸린 시간
//            System.out.println("findMembers = " + timeMs + "ms");
//        }
    }

    /**
     * id로 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
