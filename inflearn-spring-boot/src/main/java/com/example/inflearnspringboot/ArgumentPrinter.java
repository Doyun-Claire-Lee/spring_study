package com.example.inflearnspringboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ArgumentPrinter {

    public ArgumentPrinter(ApplicationArguments args) {
        System.out.println("JVM args: " + args.containsOption("foo"));
        System.out.println("Application args: " + args.containsOption("bar"));
    }
}
