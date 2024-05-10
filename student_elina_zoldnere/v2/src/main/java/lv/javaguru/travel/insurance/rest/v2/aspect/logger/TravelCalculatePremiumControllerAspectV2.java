package lv.javaguru.travel.insurance.rest.v2.aspect.logger;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.rest.common.ControllerLogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
class TravelCalculatePremiumControllerAspectV2 {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumControllerAspectV2.class);

    @Pointcut("execution(* lv.javaguru.travel.insurance.rest.v2.TravelCalculatePremiumControllerV2.calculatePremium(..))")
    public void calculatePremiumMethod() {
    }

    @Around("calculatePremiumMethod()")
    public Object logCalculatePremium(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Object response = joinPoint.proceed();

        stopwatch.stop();
        long executionTime = stopwatch.elapsed().toMillis();

        ControllerLogRequestV2.log(joinPoint);
        ControllerLogResponseV2.log(response);
        ControllerLogExecutionTime.log(executionTime);

        return response;
    }

}

