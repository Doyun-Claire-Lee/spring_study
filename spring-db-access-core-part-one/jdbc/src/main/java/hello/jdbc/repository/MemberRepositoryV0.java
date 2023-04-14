package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * JDBC - DriverManager 사용
 */
@Slf4j
@Repository
public class MemberRepositoryV0 {

    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values (?, ?)";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, member.getMemberId());
            statement.setInt(2, member.getMoney());

            statement.executeUpdate();

            return member;
        } catch (SQLException e) {
            log.error("dbError", e);
            throw new RuntimeException(e);
        } finally {
            close(conn, statement, null);
        }
    }

    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

    private void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }
    }
}
