package lv.javaguru.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumRequestExecutionTimeLoggerTest {

    TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger = new TravelCalculatePremiumRequestExecutionTimeLogger();

    @Test
    public void logTest() {
        executionTimeLogger.logExecutionTime(Stopwatch.createStarted());
    }
}
