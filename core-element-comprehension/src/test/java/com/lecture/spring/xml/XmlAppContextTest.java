package com.lecture.spring.xml;

import com.lecture.spring.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

public class XmlAppContextTest {

    @Test
    void xmlAppContext() {
        String path = new File("src/test/resources/appConfig.xml").getAbsolutePath();
        ApplicationContext ac = new GenericXmlApplicationContext(path);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
