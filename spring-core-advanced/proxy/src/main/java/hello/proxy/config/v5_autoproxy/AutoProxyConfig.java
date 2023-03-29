package hello.proxy.config.v5_autoproxy;

import hello.proxy.config.AppConfigV1;
import hello.proxy.config.AppConfigV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfigV1.class, AppConfigV2.class})
public class AutoProxyConfig {

    // 자동프록시생성기가 빈으로 등록된 모든 Advisor를 찾아서 포인트컷(조건)을 모든 빈에 적용해보고 하나라도 만족하면 프록시 객체를 빈으로 등록해준다.
    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
