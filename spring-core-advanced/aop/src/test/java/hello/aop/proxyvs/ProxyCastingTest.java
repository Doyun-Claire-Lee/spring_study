package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);    //JDK 동적프록시

        //프록시를 인터페이스로 캐스팅 -> 성공
        MemberService MemberServiceProxy = (MemberService) proxyFactory.getProxy();

        //프록시를 구체클래스로 캐스팅 -> 실패
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) proxyFactory.getProxy();
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);    //CGLIB 프록시

        //프록시를 인터페이스로 캐스팅 -> 성공
        MemberService MemberServiceProxy = (MemberService) proxyFactory.getProxy();

        //프록시를 구체클래스로 캐스팅 -> 성공
        MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) proxyFactory.getProxy();
    }

}
