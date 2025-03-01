package com.mxxikr.springintro.repository;

import com.mxxikr.springintro.domain.Member;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em; // JPA는 EntityManager로 모든 것이 동작

    public JpaMemberRepository(EntityManager em) { // EntityManager 주입
        this.em = em;
    }

    @Override
    public Member save(Member member) throws SQLException {
        em.persist(member); // JPA가 insert 쿼리 생성해서 DB에 삽입
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // JPA가 select 쿼리 생성해서 DB에서 데이터 가져옴
        return Optional.ofNullable(member); // null일 수 있으므로 Optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> member = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList(); // :name에 파라미터 값이 바인딩되어 JPQL이 실행됨
        // List로 반환하는 이유 : 이름이 같은 회원이 여러 명일 수 있음
        return member.stream().findAny();
    }

    /**
     * JPQL을 사용하여 모든 회원 조회
     * m : Member Entity 객체
     */
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
