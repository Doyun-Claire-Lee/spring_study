package com.example.inflearnspringbootinitwebmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc     //해당 어노테이션 활성화시 MVC관련 설정을 직접 정의해야 함
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //ResourceHandler를 추가
        // /m으로 시작하는 요청이 오면 Resource를 m폴더 밑에서 찾기
        registry.addResourceHandler("/m/**")
                .addResourceLocations("classpath:/m/")    //꼭 '/'로 끝내주어야 함.
                .setCachePeriod(20);
    }
}
