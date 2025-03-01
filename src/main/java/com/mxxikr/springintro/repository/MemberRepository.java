package com.mxxikr.springintro.repository;

import com.mxxikr.springintro.domain.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * 회원 저장소 인터페이스
 * 구현체가 어떤 저장소를 사용하던지 인터페이스만 바꾸면 되기 때문에 인터페이스 생성
 */
public interface MemberRepository {
    // 회원 저장용 메서드
    Member save(Member member) throws SQLException;

    // id로 회원 찾기 메서드 - null일 수 있으므로 Optional로 감싸서 반환
    Optional<Member> findById(Long id);

    // 이름으로 회원 찾기 메서드 - null일 수 있으므로 Optional로 감싸서 반환
    Optional<Member> findByName(String name);

    // 저장된 모든 회원 반환 메서드
    List<Member> findAll();
}
