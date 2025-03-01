package com.mxxikr.springintro.repository;

import com.mxxikr.springintro.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name); // 메서드 이름만으로 JPQL 생성 가능
}
