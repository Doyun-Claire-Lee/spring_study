package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        proxy.call();   //AInterface의 call메소드가 handler의 invoke()의 파라미터로 전달됨

        log.info("targetClass={}", target.getClass());  //hello.proxy.jdkdynamic.code.AImpl
        log.info("proxyClass={}", proxy.getClass());    //com.sun.proxy.$Proxy8
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        proxy.call();   //AInterface의 call메소드가 handler의 invoke()의 파라미터로 전달됨

        log.info("targetClass={}", target.getClass());  //hello.proxy.jdkdynamic.code.AImpl
        log.info("proxyClass={}", proxy.getClass());    //com.sun.proxy.$Proxy8
    }
}
