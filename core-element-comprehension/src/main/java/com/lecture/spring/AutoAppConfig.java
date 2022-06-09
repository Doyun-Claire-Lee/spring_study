package com.lecture.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // 기존에 등록했던 AppConfig, TestConfig 등을 제외하기 위해 필터를 적용해줌
)
public class AutoAppConfig {
}
