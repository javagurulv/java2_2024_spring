package lv.javaguru.travel.insurance.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TravelCalculatePremiumRequestProcessingTimeLogger {

    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestProcessingTimeLogger.class);

    void log(long time) {
        logger.info("Request processing time (ms): " + time);
    }
}