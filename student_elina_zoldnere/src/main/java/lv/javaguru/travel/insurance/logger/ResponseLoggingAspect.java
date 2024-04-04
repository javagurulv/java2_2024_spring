package lv.javaguru.travel.insurance.logger;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResponseLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingAspect.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Pointcut("execution(* lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl.calculatePremium(..))")
    public void calculatePremiumMethod() { }

    @Around("calculatePremiumMethod()")
    public Object logCalculatePremiumResponse(ProceedingJoinPoint joinPoint) throws Throwable {

        Object response = joinPoint.proceed();
        logResponse(response);

        return response;
    }

    private void logResponse(Object response) {
        if (response instanceof TravelCalculatePremiumResponse) {
            try {
                String responseJson = convertResponseToJson((TravelCalculatePremiumResponse) response);
                logger.info("RESPONSE: {}", responseJson);
            } catch (JsonProcessingException e) {
                logger.error("Error converting response to JSON", e);
            }
        }
    }

    private String convertResponseToJson(TravelCalculatePremiumResponse response) throws JsonProcessingException {
        return mapper.writeValueAsString(response);
    }

}

