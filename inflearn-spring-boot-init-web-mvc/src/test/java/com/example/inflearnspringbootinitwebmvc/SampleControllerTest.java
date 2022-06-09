package com.example.inflearnspringbootinitwebmvc;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SampleController.class)
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebClient webClient;    //htmlUnit

    @Test
    public void hi() throws Exception {
        mockMvc.perform(get("/hi"))
                .andExpect(status().isOk())
                .andExpect(view().name("hi"))
                .andExpect(model().attribute("name", "doyun"))
                .andDo(print());
    }

    @Test
    public void htmlUnitHi() throws IOException {
        // 따로 htmlunit 의존성을 추가하여 html을 전문적으로 테스트 가능
        HtmlPage page = webClient.getPage("/hi");
        HtmlHeading1 h1 = page.getFirstByXPath("//h1");
        assert h1.getTextContent().equals("doyun");
    }
}
