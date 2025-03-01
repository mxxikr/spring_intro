package com.mxxikr.springintro.repository;

import com.mxxikr.springintro.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource; // DB 연결을 위한 DataSource

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * DB 연결
     */
    @Override
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(name) values(?)";

        Connection conn = null; // DB 연결 정보
        PreparedStatement pstmt = null; // SQL문 실행
        ResultSet rs = null; // SQL문 실행 결과 저장

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // SQL문 준비

            pstmt.setString(1, member.getName()); // SQL문의 ?에 값 설정

            pstmt.executeUpdate(); // DB에 실제 쿼리 실행
            rs = pstmt.getGeneratedKeys(); // 생성된 key값 반환

            if (rs.next()) { // key값이 있으면
                member.setId(rs.getLong(1)); // key값을 member에 저장
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery(); // 조회 결과 반환

            if(rs.next()) { // 조회 결과가 있으면 member 객체에 저장
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member); // 조회 결과 반환
            } else {
                return  Optional.empty(); // 조회 결과가 없으면 empty 반환
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name); // SQL문의 ?에 값 설정

            rs = pstmt.executeQuery(); // 조회 결과 반환

            if(rs.next()) { // 조회 결과가 있으면 member 객체에 저장
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member); // 조회 결과 반환
            }

            return Optional.empty(); // 조회 결과가 없으면 empty 반환
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()) { // 조회 결과가 있으면 member 객체에 저장
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }

            return members; // 조회 결과 반환
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /**
     * Connection 반환 메서드
     */
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource); // Connection 반환
    }

    /**
     * Connection 반환 메서드
     * @param conn
     * @param pstmt
     * @param rs
     */
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close(); // ResultSet 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close(); //   PreparedStatement 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                close(conn); // Connection 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connection 반환 메서드
     * @param conn
     *
     * DB Connection을 직접 닫으면 안되고, DataSourceUtils를 통해 반환해야 함
     *  -> DataSourceUtils가 커넥션을 관리하고 있기 때문에 안전하게 반환됨
     */
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource); // Connection 반환
    }
}
