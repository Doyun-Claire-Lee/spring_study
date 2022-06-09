package com.lecture.spring.singleton;

import com.lecture.spring.AppConfig;
import com.lecture.spring.member.MemberRepository;
import com.lecture.spring.member.MemberServiceImpl;
import com.lecture.spring.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void configurationTest() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        assertThat(memberRepository1).isEqualTo(memberRepository);
        assertThat(memberRepository2).isEqualTo(memberRepository);
    }

    @Test
    void configurationDeep() {
        AppConfig appConfig = ac.getBean(AppConfig.class);

        // class com.lecture.spring.AppConfig$$EnhancerBySpringCGLIB$$3df6dbe6
        // Enhancer? -> 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 클래스를 만들고,
        //              그 클래스를 스프링 빈으로 등록한 것. 이 클래스가 싱글톤을 보장해줌
        // @Configuration을 붙여야 CGLIB이 개입하여 싱글톤을 보장해줌.
        System.out.println("appConfig.getClass() = " + appConfig.getClass());
    }
}
