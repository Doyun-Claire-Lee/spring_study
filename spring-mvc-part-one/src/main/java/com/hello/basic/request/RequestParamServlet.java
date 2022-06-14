package com.hello.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - START");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - END");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - START");
        System.out.println("request.getParameter(\"username\") = " + request.getParameter("username"));
        System.out.println("request.getParameter(\"age\") = " + request.getParameter("age"));
        System.out.println("[단일 파라미터 조회] - END");
        System.out.println();

        System.out.println("[이름이 동일한 파라미터 조회] - START");
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            System.out.println("username = " + username);
        }
        System.out.println("[이름이 동일한 파라미터 조회] - END");
    }
}
