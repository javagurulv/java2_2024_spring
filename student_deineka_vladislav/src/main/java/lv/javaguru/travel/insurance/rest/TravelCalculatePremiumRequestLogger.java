package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
class TravelCalculatePremiumRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);

    void logging(TravelCalculatePremiumRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("Request: " + json);
        } catch (JsonProcessingException e) {
            logger.error(("Error while converting request to Json"), e);
        }
    }
}
