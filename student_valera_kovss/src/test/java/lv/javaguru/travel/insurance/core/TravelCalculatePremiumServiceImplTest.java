package lv.javaguru.travel.insurance.core;


import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
class TravelCalculatePremiumServiceImplTest {

    private DateTimeService dateTimeService;
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    public void setUp() {
        dateTimeService = new DateTimeService();
        service = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }


    @Test
    public void shouldPersonFirstName() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setPersonFirstName("Valera");
        TravelCalculatePremiumResponse premiumResponse = service.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getPersonFirstName(), premiumRequest.getPersonFirstName());
    }

    @Test
    public void shouldPersonLastName() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setPersonLastName("Kovss");
        TravelCalculatePremiumResponse premiumResponse = service.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getPersonLastName(), premiumRequest.getPersonLastName());
    }

    @Test
    public void shouldAgreementDateFrom() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setAgreementDateFrom(new Date());
        TravelCalculatePremiumResponse premiumResponse = service.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getAgreementDateFrom(), premiumRequest.getAgreementDateFrom());
    }

    @Test
    public void shouldAgreementDateTo() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse premiumResponse = service.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getAgreementDateTo(), premiumRequest.getAgreementDateTo());
    }

    @Test
    public void shouldAgreementPrice() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setAgreementDateFrom(new Date(2005, 05, 15));
        premiumRequest.setAgreementDateTo(new Date(2005, 05, 20));
        TravelCalculatePremiumResponse premiumResponse = service.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getAgreementPrice(), BigDecimal.valueOf(5));
    }


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
