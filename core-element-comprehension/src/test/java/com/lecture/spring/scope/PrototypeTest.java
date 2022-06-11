package com.lecture.spring.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    /*
    스프링 컨테이너는 프로토타입 빈의 생성에만 관여하고 이후에는 관리하지 않는다.
    따라서 생성, 의존주입 까지만 스프링이 관여하고 스프링 컨테이너가 소멸하더라도 프로토타입 빈 객체의 소멸에는 관여하지 않음.
    -> @PreDestroy 메소드가 호출되지 않음.
     */

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); //스프링 컨테이너 종료
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

}
