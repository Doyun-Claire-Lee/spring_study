package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection conn = dataSource.getConnection();
        try {
            conn.setAutoCommit(false);  //트랜잭션 시작

            bizLogic(fromId, toId, money, conn);

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw new IllegalStateException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();   //커넥션 풀로 돌려줌
                } catch (Exception e) {
                    log.error("error", e);
                }
            }
        }
    }

    private void bizLogic(String fromId, String toId, int money, Connection conn) {
        Member fromMember = memberRepository.findById(conn, fromId);
        Member toMember = memberRepository.findById(conn, toId);

        memberRepository.update(conn, fromId, fromMember.getMoney() - money);
        validation(toId);
        memberRepository.update(conn, toId, toMember.getMoney() + money);
    }

    private static void validation(String toId) {
        if (toId.equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
