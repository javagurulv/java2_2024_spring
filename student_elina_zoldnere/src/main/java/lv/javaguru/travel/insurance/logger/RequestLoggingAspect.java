package lv.javaguru.travel.insurance.logger;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Pointcut("execution(* lv.javaguru.travel.insurance.core.TravelCalculatePremiumServiceImpl.calculatePremium(..))")
    public void calculatePremiumMethod() { }

    @Before("calculatePremiumMethod()")
    public void logRequest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof TravelCalculatePremiumRequest request) {
            try {
                String requestJson = convertRequestToJson(request);
                logger.info("REQUEST: {}", requestJson);
            } catch (JsonProcessingException e) {
                logger.error("Error converting request to JSON", e);
            }
        }
    }

    private String convertRequestToJson(TravelCalculatePremiumRequest request) throws JsonProcessingException {
        return mapper.writeValueAsString(request);
    }

}
