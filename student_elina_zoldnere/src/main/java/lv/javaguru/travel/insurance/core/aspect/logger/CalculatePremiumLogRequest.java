package lv.javaguru.travel.insurance.core.aspect.logger;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class CalculatePremiumLogRequest {

    private static final Logger logger = LoggerFactory.getLogger(CalculatePremiumLogRequest.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void log(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof TravelCalculatePremiumRequest request) {
            try {
                String requestJson = mapper.writeValueAsString(request);
                logger.info("REQUEST: {}", requestJson);
            } catch (JsonProcessingException e) {
                logger.error("Error converting request to JSON", e);
            }
        }
    }

}