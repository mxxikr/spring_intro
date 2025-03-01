package com.mxxikr.springintro;

import com.mxxikr.springintro.repository.*;
import com.mxxikr.springintro.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // 스프링 컨테이너가 뜰 때 스프링 빈을 등록
public class SpringConfig {
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
//    private final DataSource dataSource; // 데이터베이스 커넥션을 획득할 때 사용하는 객체
//    private final EntityManager em; // JPA는 EntityManager로 모든 것이 동작
//
//    public SpringConfig(DataSource dataSource, EntityManager em) { // 생성자를 통해 DataSource 주입
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    @Bean // 스프링 빈 등록
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource); // JdbcMemberRepository로 변경
////        return new JdbcTemplateMemberRepository(dataSource); // JdbcTemplateMemberRepository로 변경
//        return new JpaMemberRepository(dataSource); // JpaMemberRepository로 변경
//    }
}
