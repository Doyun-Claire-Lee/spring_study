package com.example.inflearnspringbootinitwebmvc.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception{
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void createUser_JSON() throws Exception {
        String userJson = "{\"username\":\"doyun\", \"password\":\"1234\"}";

        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("doyun"))
                .andExpect(jsonPath("$.password").value("1234"));
    }

    @Test
    public void createUser_XML() throws Exception {
        String userJson = "{\"username\":\"doyun\", \"password\":\"1234\"}";

//        xml로 내보내고 싶을 경우에는 의존성 추가 해주어야 함.
//        <dependency>
//            <groupId>com.fasterxml.jackson.dataformat</groupId>
//            <artifactId>jackson-dataformat-xml</artifactId>
//            <version>2.9.6</version>
//        </dependency>
        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_XML)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(xpath("/User/username").string("doyun"))
                .andExpect(xpath("/User/password").string("1234"));
    }

}
