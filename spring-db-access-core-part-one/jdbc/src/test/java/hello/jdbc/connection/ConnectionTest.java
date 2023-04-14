package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection conn1 = DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);
        Connection conn2 = DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);

        log.info("connection={}, class={}", conn1, conn1.getClass());
        log.info("connection={}, class={}", conn2, conn2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        //DriverManagerDataSource -> 항상 새로운 커넥션 생성(내부에서 DriverManager 사용)
        DriverManagerDataSource dataSource = new DriverManagerDataSource(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(ConnectionConst.URL);
        dataSource.setUsername(ConnectionConst.USERNAME);
        dataSource.setPassword(ConnectionConst.PASSWORD);
        dataSource.setMaximumPoolSize(10);  //default가 10
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection conn1 = dataSource.getConnection();
        Connection conn2 = dataSource.getConnection();

        log.info("connection={}, class={}", conn1, conn1.getClass());
        log.info("connection={}, class={}", conn2, conn2.getClass());
    }
}
