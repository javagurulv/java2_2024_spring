package lv.javaguru.travel.insurance.core.rest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponseLogger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumResponseLoggerTest {
    TravelCalculatePremiumResponseLogger logger = new TravelCalculatePremiumResponseLogger();
    @Test
    public void responseConversionToJsonString() {
        TravelCalculatePremiumResponse response = mock(TravelCalculatePremiumResponse.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 11);
        Date dateTo = calendar.getTime();
        when(response.getPersonFirstName()).thenReturn("Vladislav");
        when(response.getPersonLastName()).thenReturn("Kuznetsov");
        when(response.getAgreementDateTo()).thenReturn(dateTo);
        when(response.getAgreementDateFrom()).thenReturn(new Date());
        when(response.getErrors()).thenReturn(null);
        when(response.getAgreementPrice()).thenReturn(new BigDecimal(11));
        logger.log(response);
    }
}
