package lv.javaguru.travel.insurance.core;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeServiceTest {

    private DateTimeService dateTimeService = new DateTimeService();

    @Test
    public void noDaysFromTo() {
        Date dateFrom = newDate("01.01.2001");
        Date dateTo = newDate("01.01.2001");
        long daysFromTo = dateTimeService.calculateDateFromTo(dateFrom,dateTo);
        assertEquals(daysFromTo,0L);
    }

    @Test
    public void daysFromToIncrements() {
        Date dateFrom = newDate("01.01.2001");
        Date dateTo = newDate("02.01.2001");
        long daysFromTo = dateTimeService.calculateDateFromTo(dateFrom,dateTo);
        assertEquals(daysFromTo, 1L);
    }

    @Test
    public void daysFromToDecrements() {
        Date dateFrom = newDate("02.01.2001");
        Date dateTo = newDate("01.01.2001");
        long daysFromTo = dateTimeService.calculateDateFromTo(dateFrom,dateTo);
        assertEquals(daysFromTo, -1L);
    }

    private Date newDate(String addedDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(addedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


   //  @Test
   // public void checkDateTimeService() {
   //     TravelCalculatePremiumRequest travelRequest= new TravelCalculatePremiumRequest();
   //     travelRequest.setAgreementDateFrom(new Date());
   //     travelRequest.setAgreementDateTo(new Date());
   //     long travelCalculatePremiumRequest = travelRequest.getAgreementDateFrom().getTime() - travelRequest.getAgreementDateTo().getTime();
   //     long travelCalculatePremiumResponse = dateTimeService.calculateDateFromTo(travelRequest);
//
   //     assertEquals(travelCalculatePremiumResponse, travelCalculatePremiumRequest);
//
   //
}
