package hello.aop.exam;

import hello.aop.order.aop.exam.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ExamTest {

    @Autowired
    ExamService service;

    @Test
    void Test() {
        for (int i = 0; i < 5; i++) {
            log.info("client request i={}", i);
            service.request("data" + i);
        }
    }
}
