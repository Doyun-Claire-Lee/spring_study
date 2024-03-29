package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 * TransactionManager
 * DataSourceUtils.getConnection()
 * DataSourceUtils.releaseConnection()
 */
@Slf4j
public class MemberRepositoryV3 {

    private final DataSource dataSource;

    public MemberRepositoryV3(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, memberId);

            rs = statement.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));

                return member;
            }

            throw new NoSuchElementException("member not found, memberId=" + memberId);
        } catch (SQLException e) {
            log.error("dbError", e);
            throw new RuntimeException(e);
        } finally {
            close(conn, statement, rs);
        }
    }

    public void update(String memberId, int money) {
        String sql = "update member set money = ? where member_id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, money);
            statement.setString(2, memberId);

            int resultSize = statement.executeUpdate();
            log.info("update resultSize={}", resultSize);

        } catch (SQLException e) {
            log.error("dbError", e);
            throw new RuntimeException(e);
        } finally {
            close(conn, statement, null);
        }
    }


    public void delete(String memberId) {
        String sql = "delete from member where member_id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, memberId);

            int resultSize = statement.executeUpdate();
            log.info("delete resultSize={}", resultSize);

        } catch (SQLException e) {
            log.error("dbError", e);
            throw new RuntimeException(e);
        } finally {
            close(conn, statement, null);
        }
    }

    private Connection getConnection() throws SQLException {
        Connection conn = DataSourceUtils.getConnection(dataSource);
        log.info("get connection={}, class={}", conn, conn.getClass());

        return conn;
    }

    private void close(Connection conn, Statement st, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(st);
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
