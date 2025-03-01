package com.mxxikr.springintro.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // 스프링 빈으로 등록
@Aspect // AOP로 사용하기 위한 어노테이션
public class TimeTraceAop {

    // AOP는 메서드 호출과 관련된 정보를 제공하는 ProceedingJoinPoint를 파라미터로 받음
    @Around("execution(* com.mxxikr.springintro..*(..))") // AOP 적용 범위 지정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis(); // 현재 시간을 밀리초로 반환
        System.out.println("START: " + joinPoint.toString()); // 메서드 이름 출력
        try {
            return joinPoint.proceed(); // 실제 메서드 호출
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms"); // 메서드 이름과 걸린 시간 출력
        }
    }
}
