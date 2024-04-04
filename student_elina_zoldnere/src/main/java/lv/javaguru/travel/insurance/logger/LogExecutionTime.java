package lv.javaguru.travel.insurance.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class LogExecutionTime {

    private static final Logger logger = LoggerFactory.getLogger(LogExecutionTime.class);

    public static void log(long executionTime) {
        logger.info("Request processing time (ms): {}", executionTime);
    }

}