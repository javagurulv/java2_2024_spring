package lv.javaguru.travel.insurance.rest.aspect.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class ControllerLogRequest {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogRequest.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void log(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            Object request = args[0];
            if (request instanceof TravelCalculatePremiumRequestV1
                    || request instanceof TravelCalculatePremiumRequestV2) {
                try {
                    String requestJson = mapper.writeValueAsString(request);
                    logger.info("REQUEST: {}", requestJson);
                } catch (JsonProcessingException e) {
                    logger.error("Error converting request to JSON", e);
                }
            } else if (request instanceof String) {
                logger.info("REQUEST: {}", request);
            } else {
                logger.warn("Unexpected request object type: {}", request.getClass().getName());
            }
        }
    }

}