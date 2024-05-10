package lv.javaguru.travel.insurance.rest.v2.aspect.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class ControllerLogResponseV2 {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogResponseV2.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void log(Object response) {
        if (response instanceof TravelCalculatePremiumResponseV1) {
            try {
                String responseJson = mapper.writeValueAsString(response);
                logger.info("RESPONSE: {}", responseJson);
            } catch (JsonProcessingException e) {
                logger.error("Error converting response to JSON", e);
            }
        }
    }

}