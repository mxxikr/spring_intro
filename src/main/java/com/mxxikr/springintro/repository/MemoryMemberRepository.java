package com.mxxikr.springintro.repository;

import com.mxxikr.springintro.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    // 실무에서는 동시성 문제 고려해 ConcurrentHashMap 사용
    private static Map<Long, Member> store = new HashMap<>(); // 회원 저장소 생성
    // 실무에서는 동시성 문제 고려해 AtomicLong 사용
    private static long sequence = 0L; // id 생성을 위한 변수

    /**
     * 회원 저장
     * @param member
     */
    @Override
    public Member save(Member member) { // member와 id를 저장하고 반환
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * id로 회원 찾기
     * @param id
     * @return
     */
    @Override
    public Optional<Member> findById(Long id) {
        // null이 반환될 수 있으므로 Optional로 감싸서 반환
        return Optional.ofNullable(store.get(id));
    }

    /**
     * 이름으로 회원 찾기
     * @param name
     * @return
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // store에서 name과 같은 member 찾고 하나라도 찾으면 반환
    }

    /**
     * 저장된 모든 회원 반환
     * @return
     */
    @Override
    public List<Member> findAll() {
        // store에 있는 모든 member 반환
        return new ArrayList<>(store.values());
    }

    /**
     * 저장소 초기화
     */
    public void clearStore() {
        store.clear();
    }
}
