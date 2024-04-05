package lv.javaguru.travel.insurance.core.aspect.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class CalculatePremiumLogExecutionTime {

    private static final Logger logger = LoggerFactory.getLogger(CalculatePremiumLogExecutionTime.class);

    public static void log(long executionTime) {
        logger.info("Request processing time (ms): {}", executionTime);
    }

}