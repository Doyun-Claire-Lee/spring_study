package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //request.getParameter("key")
    // -> GET   host:port/uri?key=value
    // -> POST  contentType:x-www-form-urlEncoded인 경우

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    // @RequestParam 이름 생략
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    // @RequestParam 생략
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        // int는 기본형이기 때문에 null값을 가질 수 없음
        // -> age값에 아무것도 보내지 않으면 500에러 발생
        // -> 참조형인 Integer로 변경

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(defaultValue = "-1") int age) {

        // parameter 값이 null일 뿐만 아니라 빈문자열("")일 경우에도 default값 적용됨.

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username,
                                   @RequestParam int age) {

        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData) {
        // @ModelAttribute 동작과정
        // 1. HelloData 객체 생성
        // 2. 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾아 setter를 호출한다.

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v3")
    public String modelAttributeV3(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }
}
