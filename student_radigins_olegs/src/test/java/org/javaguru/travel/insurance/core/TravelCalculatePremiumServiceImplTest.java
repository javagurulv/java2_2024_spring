package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumServiceImplTest {
   private DateTimeService dateTimeService;
   private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;
   private TravelCalculatePremiumRequest request;
   private TravelCalculatePremiumResponse response;

    @BeforeEach
    public void setUp(){
         request = createReadyMadeRequest();
         //dateTimeService = new DateTimeService();
        dateTimeService = mock(DateTimeService.class);
        when(dateTimeService.daysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(31L);
         travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl(dateTimeService);
         response = travelCalculatePremiumService.calculatePremium(request);
}


    @Test
    public void shouldTestResponseFirstName() {
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());

    }

    @Test
    public void shouldTestResponseLastname() {
        assertEquals(response.getPersonLastName(), request.getPersonLastName());

    }

    @Test
    public void shouldTestResponseDateFrom() {
        assertEquals(response.getAgreementDateFrom(),request.getAgreementDateFrom());

    }

    @Test
    public void shouldTestResponseDateTo() {
        assertEquals(response.getAgreementDateTo(),request.getAgreementDateTo());

    }


    @Test
    public void shouldTestResponseAgreementPrice() {
        assertEquals(response.getAgreementPrice(),new BigDecimal(31));

    }
    private TravelCalculatePremiumRequest createReadyMadeRequest(){
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Olegs");
        request.setPersonLastName("Radigins");
        request.setAgreementDateFrom(new Date(2000,0,1));
        request.setAgreementDateTo(new Date(2000,1,1));
        return request;
    }


}