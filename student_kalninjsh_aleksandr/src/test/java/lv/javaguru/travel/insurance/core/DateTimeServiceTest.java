package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeServiceTest {

    private DateTimeService service = new DateTimeService();

    @Test
    public void calculateAgreementDaysBetweenDates() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setAgreementDateFrom(new Date(2005, 05, 15));
        premiumRequest.setAgreementDateTo(new Date(2005, 05, 20));
        BigDecimal numberOfDaysBetweenDates = service.calculateAgreementDaysBetweenDates(premiumRequest);
        assertEquals(numberOfDaysBetweenDates, BigDecimal.valueOf(5));
    }

}