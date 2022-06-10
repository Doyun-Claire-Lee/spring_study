package com.lecture.spring.autowired;

import com.lecture.spring.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    @Configuration
    static class TestBean {

        // 파라미터인 Member는 빈으로 등록되지 않는 클래스 이므로 스프링이 관여하지 않음

        @Autowired(required = false)
        public void setNoBean1(Member nobean1) {
            // required = false 인 경우 메소드 자체가 호출되지 않는다.
            System.out.println("nobean1 = " + nobean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member nobean2) {
            // nobean2 = null
            System.out.println("nobean2 = " + nobean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> nobean3) {
            // nobean3 = Optional.empty
            System.out.println("nobean3 = " + nobean3);
        }
    }
}
