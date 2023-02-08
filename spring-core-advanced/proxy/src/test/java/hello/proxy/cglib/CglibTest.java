package hello.proxy.cglib;


import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("targetClass={}", target.getClass());  //hello.proxy.common.ConcreteService
        log.info("proxyClass={}", proxy.getClass());    //hello.proxy.common.ConcreteService$$EnhancerByCGLIB$$1c94e8ce

        proxy.call();
    }

}
