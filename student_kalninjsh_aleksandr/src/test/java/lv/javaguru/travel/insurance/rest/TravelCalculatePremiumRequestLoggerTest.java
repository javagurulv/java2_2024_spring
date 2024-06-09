package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

public class TravelCalculatePremiumRequestLoggerTest {

    TravelCalculatePremiumRequestLogger requestLogger = new TravelCalculatePremiumRequestLogger();

    @Mock
    TravelCalculatePremiumRequest request;

    @Test
    public void testLog() {
        requestLogger.log(request);
    }
}
