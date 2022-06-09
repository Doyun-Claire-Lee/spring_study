package com.example.inflearnspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// = @SpringBootConfiguration + @ComponentScan + @EnableAutoConfiguration
public class InflearnSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(InflearnSpringBootApplication.class);
//        application.setWebApplicationType(WebApplicationType.NONE);
//        application.addListeners(new SampleApplicationStartListener()); //Listener 직접 추가
        application.run(args);
    }

    /*
    @Bean
    public ServletWebServerFactory serverFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());

        return tomcat;
    }

    private Connector createStandardConnector() {
        // application.properties에 ssl 관련 설정을 해놓았기 때문에 Https만 가능한 상태
        // Http도 가능하게 만들기 위해 기본 Connector를 추가해주는 작업
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);

        return connector;
    }
    */
}
