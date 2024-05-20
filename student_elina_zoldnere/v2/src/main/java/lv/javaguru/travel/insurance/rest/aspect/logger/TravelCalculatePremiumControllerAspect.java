package lv.javaguru.travel.insurance.rest.aspect.logger;

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
class TravelControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(TravelControllerAspect.class);

    @Pointcut("execution(* lv.javaguru.travel.insurance.rest.*.*Controller*.calculatePremium(..)) || " +
            "execution(* lv.javaguru.travel.insurance.rest.internal.*Controller*.getAgreement(..))")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object logControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();

        Object response = joinPoint.proceed();

        stopwatch.stop();
        long executionTime = stopwatch.elapsed().toMillis();

        ControllerLogRequest.log(joinPoint);
        ControllerLogResponse.log(response);
        ControllerLogExecutionTime.log(executionTime);

        return response;
    }

}


