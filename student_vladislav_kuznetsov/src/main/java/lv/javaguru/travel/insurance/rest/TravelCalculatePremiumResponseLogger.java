package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelCalculatePremiumResponseLogger {
    @Autowired
    TravelCalculatePremiumResponse response;

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);
    public void log(TravelCalculatePremiumResponse response){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonResponseString = mapper.writeValueAsString(response);
            logger.info("RESPONSE: " + jsonResponseString);
        } catch (Exception e){
            logger.error("ERROR converting response to JSON" + e.getMessage());
        }
    }
}
