package lv.javaguru.travel.insurance.loggers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestResponseExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(RequestResponseExecutionTimeLogger.class);
    public void logTime(long time){
        logger.info("execution time: " + time + " ms");
    }
}
