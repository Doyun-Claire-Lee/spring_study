package com.example.inflearnspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ExtraApplicationRunner implements ApplicationRunner {

    @Autowired
    SampleCustomProperties properties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Do something with application arguments(given with "--" when application started)
        System.out.println("sample.name=" + properties.getName());
        System.out.println("sample.age=" + properties.getAge());
        System.out.println("sample.gender=" + properties.getGender());
    }
}
