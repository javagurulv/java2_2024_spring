package lv.javaguru.travel.insurance.rest.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ControllerLogExecutionTime {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLogExecutionTime.class);

    public static void log(long executionTime) {
        logger.info("Request processing time (ms): {}", executionTime);
    }

}