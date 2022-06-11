package com.lecture.spring.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        // 프로토타입 빈을 멤버변수로 가지고있는 싱글톤 빈의 생성 시점에 의존성을 주입받음
        // -> 동일한 프로토타입 빈이 계속 사용됨(프로토타입이 아니게 됨)
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
    }

    @Test
    void singletonClientUsePrototypeSpringProvider() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanWithSpringProvider.class, PrototypeBean.class);

        ClientBeanWithSpringProvider clientBean1 = ac.getBean(ClientBeanWithSpringProvider.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanWithSpringProvider clientBean2 = ac.getBean(ClientBeanWithSpringProvider.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototypeJavaProvider() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBeanWithJavaProvider.class, PrototypeBean.class);

        ClientBeanWithJavaProvider clientBean1 = ac.getBean(ClientBeanWithJavaProvider.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBeanWithJavaProvider clientBean2 = ac.getBean(ClientBeanWithJavaProvider.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean;

        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();

            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBeanWithSpringProvider {

        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public ClientBeanWithSpringProvider(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            // Dependency Lookup(DL)
            // getObject() 호출 -> 스프링 내부에서 스프링 컨테이너를 통해 해당 빈을 찾아서 반환
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();

            return prototypeBean.getCount();
        }

    }

    @Scope("singleton")
    static class ClientBeanWithJavaProvider {

        @Autowired
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public ClientBeanWithJavaProvider(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();

            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }

}
