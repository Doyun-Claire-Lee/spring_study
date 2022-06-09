package com.example.inflearnspringboot;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/*
스프링에서 공식적으로 추천하는 현재 web server 정보 확인하는 방법
 */

@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        ServletWebServerApplicationContext applicationContext = event.getApplicationContext();
        int port = applicationContext.getWebServer().getPort();
        System.out.println("port: " + port);
    }
}
