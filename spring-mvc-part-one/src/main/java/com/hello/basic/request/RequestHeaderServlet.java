package com.hello.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("=== REQUEST-HEADER-START ===");
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ":" + request.getHeader(headerName)));
        System.out.println("=== REQUEST-HEADER-END ===");
        System.out.println();
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("=== REQUEST-LINE-START ===");
        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());
        System.out.println("=== REQUEST-LINE-END ===");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest request) {
        // TODO 붙여넣기
    }

    private void printEtc(HttpServletRequest request) {
        // TODO 붙여넣기
    }
}
