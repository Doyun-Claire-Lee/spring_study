package com.hello.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller           // @Component + @RequestMapping
//@Component          // Bean으로 등록
//@RequestMapping     // RequestMappingHandlerMapping 클래스가 해당 클래스를 찾을 수 있게 해줌
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}