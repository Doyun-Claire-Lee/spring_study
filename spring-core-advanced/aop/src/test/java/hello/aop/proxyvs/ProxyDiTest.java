package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDiAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDiAspect.class)
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})   //JDK 동적 프록시
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})   //CGLIB 동적 프록시
public class ProxyDiTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;    //JDK 동적 프록시일 경우 의존주입이 되지 않음.(에러 발생)

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());

        memberServiceImpl.hello("hello");
    }
}
