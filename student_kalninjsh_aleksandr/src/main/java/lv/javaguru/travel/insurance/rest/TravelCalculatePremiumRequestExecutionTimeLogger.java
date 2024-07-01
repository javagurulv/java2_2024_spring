package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TravelCalculatePremiumRequestExecutionTimeLogger {

    public static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);
    public void logExecutionTime(Stopwatch stopwatch) {
        stopwatch.stop();
        long duration = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        logger.info("Request processed in ms: " + duration);
    }



}
