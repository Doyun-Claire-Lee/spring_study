package com.lecture.spring.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    /*
    스프링 빈의 이벤트 라이프사이클(싱글톤 빈)
    스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입(세터, 필드) -> 초기화 콜백 -> 빈 사용 -> 소멸 전 콜백 -> 스프링 종료
     */

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        // context 닫아주기
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // String destroyMethod() default AbstractBeanDefinition.INFER_METHOD;
        // destroyMethod 는 기본값이 INFER_METHOD -> 종료시 호출하는 메소드의 이름이 close, shutdown 이라는 점을 추론하여 해당 메소드를 호출해줌.
        // 외부 라이브러리의 객체를 빈으로 등록해서 사용해야 할 때도 적용할 수 있다.
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");

            return networkClient;
        }
    }
}
