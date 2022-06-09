package com.example.inflearnspringboot;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// Application 시작 이벤트
// ApplicationContext가 생성되기 이전 시점의 이벤트들은 Bean으로 등록하더라도 사용할 수 없음
// -> 따라서 직접 등록해주어야 한다.
public class SampleApplicationStartListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("Spring boot Started!!!");
    }
}
