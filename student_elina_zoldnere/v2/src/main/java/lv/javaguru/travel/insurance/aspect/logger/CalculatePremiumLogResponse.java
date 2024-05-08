package lv.javaguru.travel.insurance.aspect.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class CalculatePremiumLogResponse {

    private static final Logger logger = LoggerFactory.getLogger(CalculatePremiumLogResponse.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void log(Object response) {
        if (response instanceof TravelCalculatePremiumCoreResult result) {
            try {
                // Or should I get errors and agreement and write values separately?
                String responseJson = mapper.writeValueAsString(result);
                logger.info("RESPONSE: {}", responseJson);
            } catch (JsonProcessingException e) {
                logger.error("Error converting response to JSON", e);
            }
        }
    }

}