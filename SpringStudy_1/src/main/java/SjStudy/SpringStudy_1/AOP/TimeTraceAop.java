package SjStudy.SpringStudy_1.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect    //@Aspect 어노테이션이 있어서 AOP를 사용가능
@Component   //빈을 등록해서 써도되고 컴포넌트 스캔 후 사용을 해도된다.
public class TimeTraceAop {

    @Around("execution(* SjStudy.SpringStudy_1..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{  //다음메소드로 진행
        Long start = System.currentTimeMillis();
        System.out.printf("Start : " + joinPoint.toString());  // 어떤메소드를 call 하는지
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.printf("End : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
