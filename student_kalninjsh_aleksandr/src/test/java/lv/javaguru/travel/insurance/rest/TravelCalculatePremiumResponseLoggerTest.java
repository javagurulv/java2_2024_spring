package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TravelCalculatePremiumResponseLoggerTest {

    TravelCalculatePremiumResponseLogger responseLogger = new TravelCalculatePremiumResponseLogger();

    @Mock
    TravelCalculatePremiumResponse response;

    @Test
    public void logTes() {
        responseLogger.log(response);
    }
}
