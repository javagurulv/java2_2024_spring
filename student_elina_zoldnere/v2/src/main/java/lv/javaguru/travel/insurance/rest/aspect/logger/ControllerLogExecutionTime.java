package lv.javaguru.travel.insurance.rest.aspect.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class ControllerLogExecutionTime {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogExecutionTime.class);

    public static void log(long executionTime) {
        logger.info("Request processing time (ms): {}", executionTime);
    }

}