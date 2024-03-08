package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
    TravelCalculatePremiumServiceImpl calculatePremiumService = new TravelCalculatePremiumServiceImpl();

    @Test
    public void expectTrueInFirstName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Petrov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse actualResult = calculatePremiumService.calculatePremium(request);
        assertEquals(request.getPersonFirstName(), actualResult.getPersonFirstName());
    }

    @Test
    public void expectTrueInLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Petrov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse actualResult = calculatePremiumService.calculatePremium(request);
        assertEquals(request.getPersonLastName(), actualResult.getPersonLastName());
    }

    @Test
    public void expectTrueInDateFrom() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Petrov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse actualResult = calculatePremiumService.calculatePremium(request);
        assertEquals(request.getAgreementDateFrom(), actualResult.getAgreementDateFrom());
    }

    @Test
    public void expectTrueInDateTo() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Petrov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse actualResult = calculatePremiumService.calculatePremium(request);
        assertEquals(request.getAgreementDateTo(), actualResult.getAgreementDateTo());
    }
}