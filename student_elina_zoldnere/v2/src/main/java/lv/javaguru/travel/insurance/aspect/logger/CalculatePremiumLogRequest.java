package lv.javaguru.travel.insurance.aspect.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
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
        if (args.length > 0 && args[0] instanceof TravelCalculatePremiumCoreCommand command) {
            try {
                String requestJson = mapper.writeValueAsString(command);
                logger.info("REQUEST: {}", requestJson);
            } catch (JsonProcessingException e) {
                logger.error("Error converting request to JSON", e);
            }
        }
    }

}