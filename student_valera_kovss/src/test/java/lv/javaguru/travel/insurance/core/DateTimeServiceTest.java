
package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class DateTimeServiceTest {


    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void shouldAgreementTimeService() {
        TravelCalculatePremiumRequest travelRequest= new TravelCalculatePremiumRequest();
        travelRequest.setAgreementDateFrom(new Date());
        travelRequest.setAgreementDateTo(new Date());
        long travelCalculatePremiumRequest = travelRequest.getAgreementDateFrom().getTime() - travelRequest.getAgreementDateTo().getTime();
        long travelCalculatePremiumResponse = dateTimeService.calculateDateFromTo(travelRequest);

        assertEquals(travelCalculatePremiumResponse, travelCalculatePremiumRequest);

    }
}
