package com.example.inflearnspringboot.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc   //Mock Environment와 함께 써주어야 함.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleTests {

//    @Autowired
//    MockMvc mockMvc;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    WebTestClient webTestClient;    //비동기성

    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello() throws Exception {
        //@SpringBootTest(MOCK), @AutoConfigurationMockMvc를 이용한 테스트
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("hello"))
//                .andDo(print());

        when(mockSampleService.getName()).thenReturn("dev");

        //@SpringBootTest(RANDOM_PORT), TestRestTemplate, mockBean을 사용한 테스트
//        String result = restTemplate.getForObject("/hello", String.class);
//        assertThat(result).isEqualTo("hello dev");

        //@SpringBootTest(RANDOM_PORT), WebTestClient, mockBean을 사용한 테스트
        //webflux 의존성을 추가해주어야 쓸 수 있음.
        webTestClient.get().uri("hello").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello dev");
    }
}
