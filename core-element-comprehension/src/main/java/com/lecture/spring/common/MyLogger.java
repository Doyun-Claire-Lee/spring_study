package com.lecture.spring.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request")
// Scope가 request인 경우에 어플리케이션 구동하며 해당 빈을 다른 빈에 주입해 줄 때 에러 발생
// -> request가 와야 빈이 생성되는데 어플리케이션 구동시에는 request를 받을 수 없으므로 bean을 생성할 수 없음.
// org.springframework.beans.factory.support.ScopeNotActiveException: Error creating bean with name 'myLogger': Scope 'request' is not active for the current thread; consider defining a scoped proxy for this bean if you intend to refer to it from a singleton
// ObjectProvider 혹은 proxyMode를 사용하여 해결할 수 있다.
// proxyMode: MyLogger의 가짜 프록시 클래스를 만들어 다른 빈에 미리 주입해 둘 수 있다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    // requestURL은 MyLogger bean이 생성되는 시점에 알 수 없으므로 추후에 주입해준다.
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean created: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean closed: " + this);
    }
}
