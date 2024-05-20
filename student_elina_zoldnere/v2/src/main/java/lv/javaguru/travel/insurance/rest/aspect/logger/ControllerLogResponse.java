package lv.javaguru.travel.insurance.rest.aspect.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.internal.TravelGetAgreementResponse;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class ControllerLogResponse {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogResponse.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void log(Object response) {
        if (response instanceof TravelCalculatePremiumResponseV1
                || response instanceof TravelCalculatePremiumResponseV2
                || response instanceof TravelGetAgreementResponse) {
            try {
                String responseJson = mapper.writeValueAsString(response);
                logger.info("RESPONSE: {}", responseJson);
            } catch (JsonProcessingException e) {
                logger.error("Error converting response to JSON", e);
            }
        } else {
            logger.warn("Unexpected response object type: {}", response.getClass().getName());
        }
    }

}