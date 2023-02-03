package hello.proxy;

import hello.proxy.config.AppConfigV1;
import hello.proxy.config.AppConfigV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AppConfigV1.class, AppConfigV2.class})  //스프링 빈으로 등록 - app패키지부터 스캔하도록 되어있기 때문에
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

}
