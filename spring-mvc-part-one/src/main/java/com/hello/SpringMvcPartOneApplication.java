package com.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringMvcPartOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcPartOneApplication.class, args);
    }
}
