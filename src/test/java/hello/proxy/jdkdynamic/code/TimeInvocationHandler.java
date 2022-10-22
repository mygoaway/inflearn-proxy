package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
// InvocationHandler 인터페이를 구현해야 JDK 동적 프로시를 적용할 수 있다.
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target; // 동적 프록시가 호출할 대상
    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = method.invoke(target, args); // 리플렉션을 사용해서 'target' 인스턴스의 메서드를 실행한다.

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;

    }
}
