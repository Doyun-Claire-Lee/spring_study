package com.hello.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/old-controller") // Component 이름
public class OldController implements Controller {

    /*
    http://localhost:8080/springmvc/old-controller 으로 호출하면 아래 메소드가 실행됨
    -> "스프링 빈의 이름으로 핸들러(컨트롤러)를 찾을 수 있는 핸들러 매핑"과
    -> "Controller 인터페이스를 실행할 수 있는 핸들러 어댑터"가 이미 스프링에 구현되어 있기 때문
     */

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}
