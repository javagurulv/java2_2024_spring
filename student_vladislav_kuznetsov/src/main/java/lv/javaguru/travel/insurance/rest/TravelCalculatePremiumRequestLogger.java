package lv.javaguru.travel.insurance.rest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);
    void log(TravelCalculatePremiumRequest request){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonRequestString = mapper.writeValueAsString(request);
            logger.info("REQUEST: " + jsonRequestString);
        } catch (Exception e){
            logger.error("ERROR converting request to JSON" + e.getMessage());
        }
    }

}
