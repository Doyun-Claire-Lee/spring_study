package com.example.inflearnspringbootinitwebmvc.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/user")
    public @ResponseBody User user(@RequestBody User user){
        // @ResponseBody, @RequestBody
        // - HttpMessageConverter가 자동적으로 객체 <-> 문자열간 변환을 해줌
        // - 기본적으로 객체일 경우 Json형식으로 변환됨(JsonMessageConverter)
        // - @RestController 애노테이션이 이미 있는 경우 @ResponseBody 생략 가능
        return user;
    }

    @PostMapping("/users/create")
    public User create(@RequestBody User user) {
        return user;
    }
}
