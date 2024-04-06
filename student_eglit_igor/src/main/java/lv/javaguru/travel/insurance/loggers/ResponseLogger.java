package lv.javaguru.travel.insurance.loggers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResponseLogger {
    private static final Logger logger = LoggerFactory.getLogger(ResponseLogger.class);

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public void logResponse(TravelCalculatePremiumResponse response) {
        try {
            String jsonRequest = objectMapper.writeValueAsString(response);
            logger.info("Response: {}", jsonRequest);
        } catch (JsonProcessingException e) {
            logger.error("Error converting response to JSON", e);
        }
    }
}
