package lv.javaguru.travel.insurance.loggers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestLogger {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public void logRequest(TravelCalculatePremiumRequest request) {
        try {
            String jsonRequest = objectMapper.writeValueAsString(request);
            logger.info("Request JSON: {}", jsonRequest);
        } catch (JsonProcessingException e) {
            logger.error("Error converting request to JSON", e);
        }
    }
}

