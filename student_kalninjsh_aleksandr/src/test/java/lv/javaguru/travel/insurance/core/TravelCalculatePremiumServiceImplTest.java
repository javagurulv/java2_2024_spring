package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl premiumService = new TravelCalculatePremiumServiceImpl();

    @Test
    public void personFirstName() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setPersonFirstName("Tom");
        TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getPersonFirstName(), premiumRequest.getPersonFirstName());
    }

    @Test
    public void personLastName() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setPersonLastName("Sawyer");
        TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getPersonLastName(), premiumRequest.getPersonLastName());
    }

    @Test
    public void agreementDateFrom() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setAgreementDateFrom(new Date());
        TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getAgreementDateFrom(), premiumRequest.getAgreementDateFrom());
    }

    @Test
    public void agreementDateTo() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getAgreementDateTo(), premiumRequest.getAgreementDateTo());
    }

    @Test
    public void agreementPrice() {
        TravelCalculatePremiumRequest premiumRequest = new TravelCalculatePremiumRequest();
        premiumRequest.setAgreementDateFrom(new Date(2005, 05, 15));
        premiumRequest.setAgreementDateTo(new Date(2005, 05, 20));
        TravelCalculatePremiumResponse premiumResponse = premiumService.calculatePremium(premiumRequest);
        assertEquals(premiumResponse.getAgreementPrice(), BigDecimal.valueOf(5));
    }



}