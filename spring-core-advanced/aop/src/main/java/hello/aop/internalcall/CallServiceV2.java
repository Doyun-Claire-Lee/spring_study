package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CallServiceV2 {

    //    private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public void external() {
        log.info("call external");
//        applicationContext.getBean(CallServiceV2.class).internal();
        callServiceProvider.getObject().internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
