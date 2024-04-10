package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumServiceImplTest {


    private DateTimeService service;
    private TravelCalculatePremiumServiceImpl premiumService;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void setUp() {
        request = checkingTheRequestForAllFields();
        service = mock(DateTimeService.class);
        when(service.calculateAgreementDaysBetweenDates(request)).thenReturn(new BigDecimal(5));
        premiumService = new TravelCalculatePremiumServiceImpl(service);

    }

    @Test
    public void personFirstName() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void personLastName() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void agreementDateFrom() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void agreementDateTo() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void calculateAgreementDaysBetweenDates() {
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), new BigDecimal(5));

    }

    private TravelCalculatePremiumRequest checkingTheRequestForAllFields() {
        request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Tom");
        request.setPersonLastName("Sawyer");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        return request;

    }


}