package com.mxxikr.springintro.repository;

import com.mxxikr.springintro.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate; //JdbcTemplate 사용

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource); // JdbcTemplate을 사용하기 위해 DataSource 주입
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); // SQL문을 생성해주는 객체
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); // 테이블명과 key값을 설정

        Map<String, Object> parameters = new HashMap<>(); // SQL문에 들어갈 값을 설정
        parameters.put("name", member.getName()); // SQL문에 name값 설정

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)); // SQL문 실행 후 key값 반환
        member.setId(key.longValue()); // key값을 member에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id); // SQL문 실행 결과를 Member 객체로 매핑
        return result.stream().findAny(); // 결과 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = jdbcTemplate.query("select * from mebmer", memberRowMapper());
        return result;
    }

    /**
     * RowMapper 생성 : SQL문 실행 결과(ResultSet)를 Member 객체로 매핑
     * @return
     */
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            };
//          return new RowMapper<Member>() { // RowMapper를 이용해 Member 객체로 매핑
//              @Override
//              public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//                  Member member = new Member();
//                  member.setId(rs.getLong("id"));
//                  member.setName(rs.getString("name"));s
//                  return member;
//              }
//          }
    }
}
