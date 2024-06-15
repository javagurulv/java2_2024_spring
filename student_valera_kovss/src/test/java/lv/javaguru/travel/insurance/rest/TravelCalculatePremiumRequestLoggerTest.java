package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TravelCalculatePremiumRequestLoggerTest {

    TravelCalculatePremiumRequestLogger requestPremiumLogger = new TravelCalculatePremiumRequestLogger();

    @Mock
    TravelCalculatePremiumRequest request;

    @Test
    void testLog() {
        requestPremiumLogger.log(request);
    }
}

