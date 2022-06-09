package com.example.inflearnspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

//@TestPropertySource(properties = "name=test-property-source")
@TestPropertySource(locations = "classpath:test.properties")
@SpringBootTest(properties = "name=spring-boot-test-attributes")
class InflearnSpringBootApplicationTests {

    @Autowired
    Environment environment;

    @Test
    void contextLoads() {
        String name = environment.getProperty("name");      //Environment를 통해 property key로 해당 값을 가져옴
        System.out.println("name=" + name);
    }
}
