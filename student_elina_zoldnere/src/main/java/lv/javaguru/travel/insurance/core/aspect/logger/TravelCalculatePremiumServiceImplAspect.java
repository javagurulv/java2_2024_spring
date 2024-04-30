package lv.javaguru.travel.insurance.core.aspect.logger;

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
class TravelCalculatePremiumServiceImplAspect {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumServiceImplAspect.class);

    @Pointcut("execution(* lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumServiceImpl.calculatePremium(..))")
    public void calculatePremiumMethod() {
    }

    @Around("calculatePremiumMethod()")
    public Object logCalculatePremium(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Object response = joinPoint.proceed();

        stopwatch.stop();
        long executionTime = stopwatch.elapsed().toMillis();

        CalculatePremiumLogRequest.log(joinPoint);
        CalculatePremiumLogResponse.log(response);
        CalculatePremiumLogExecutionTime.log(executionTime);

        return response;
    }

}

