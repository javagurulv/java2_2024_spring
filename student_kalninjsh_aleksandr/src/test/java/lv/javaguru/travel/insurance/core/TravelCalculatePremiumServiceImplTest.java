package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {


    DateTimeService service = new DateTimeService();
    TravelCalculatePremiumServiceImpl premiumService = new TravelCalculatePremiumServiceImpl(service);

    @Test
    public void personFirstName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Tom");
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void personLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Sawyer");
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void agreementDateFrom() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(new Date());
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void agreementDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void calculateAgreementDaysBetweenDates() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(new Date(2005, 05, 15));
        request.setAgreementDateTo(new Date(2005, 05, 20));
        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);
        assertEquals(response.getAgreementPrice(), BigDecimal.valueOf(5));

    }



}