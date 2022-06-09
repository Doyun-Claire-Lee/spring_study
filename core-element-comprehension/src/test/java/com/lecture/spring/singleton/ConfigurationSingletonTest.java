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
        System.out.println("appConfig.getClass() = " + appConfig.getClass());
    }
}
