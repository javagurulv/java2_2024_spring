package lv.javaguru.travel.insurance.logger;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatePremiumLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(CalculatePremiumLoggingAspect.class);

    @Pointcut("execution(* lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl.calculatePremium(..))")
    public void calculatePremiumMethod() {
    }

    @Around("calculatePremiumMethod()")
    public Object logCalculatePremium(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Object response = joinPoint.proceed();

        stopwatch.stop();
        long executionTime = stopwatch.elapsed().toMillis();

        LogRequest.log(joinPoint);
        LogResponse.log(response);
        LogExecutionTime.log(executionTime);

        return response;
    }

}

