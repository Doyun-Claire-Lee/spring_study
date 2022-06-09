package com.example.inflearnspringboot.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    SampleService service;

    @GetMapping("/hello")
    public String hello() {
        return "hello " + service.getName();
    }
}
