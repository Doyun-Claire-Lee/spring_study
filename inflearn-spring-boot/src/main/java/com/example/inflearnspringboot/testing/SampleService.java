package com.example.inflearnspringboot.testing;

import org.springframework.stereotype.Service;

@Service
public class SampleService {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
