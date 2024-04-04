package lv.javaguru.travel.insurance.logger;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class LogResponse {

    private static final Logger logger = LoggerFactory.getLogger(LogResponse.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void log(Object response) {
        if (response instanceof TravelCalculatePremiumResponse) {
            try {
                String responseJson = mapper.writeValueAsString(response);
                logger.info("RESPONSE: {}", responseJson);
            } catch (JsonProcessingException e) {
                logger.error("Error converting response to JSON", e);
            }
        }
    }

}