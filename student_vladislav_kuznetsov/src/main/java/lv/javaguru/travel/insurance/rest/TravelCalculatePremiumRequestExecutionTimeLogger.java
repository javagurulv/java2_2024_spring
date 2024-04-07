package lv.javaguru.travel.insurance.rest;
import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumRequestExecutionTimeLogger {
    private static final Logger logger = LoggerFactory.getLogger(TravelCalculatePremiumRequestExecutionTimeLogger.class);
    void logExecutionTime(Stopwatch stopwatch) {
        stopwatch.stop();
        long elapsedMillis = stopwatch.elapsed().toMillis();
        logger.info("Request processing time (ms): " + elapsedMillis);
    }
}