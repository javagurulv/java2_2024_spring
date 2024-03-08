package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
class TravelCalculatePremiumServiceImplTest {
    TravelCalculatePremiumServiceImpl calculatePremiumService = new TravelCalculatePremiumServiceImpl();

    @Test
    public void expectTrue() {
        String personFirstName = "Ivan";
        String personLastName = "Petrov";
        Date agreementDateFrom = new Date();
        Date agreementDateTo = new Date();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        TravelCalculatePremiumResponse expect = new TravelCalculatePremiumResponse();
        request.setPersonFirstName(personFirstName);
        request.setPersonLastName(personLastName);
        request.setAgreementDateFrom(agreementDateFrom);
        request.setAgreementDateTo(agreementDateTo);
        TravelCalculatePremiumResponse actualResult = calculatePremiumService.calculatePremium(request);
        expect.setPersonFirstName(personFirstName);
        expect.setPersonLastName(personLastName);
        expect.setAgreementDateFrom(agreementDateFrom);
        expect.setAgreementDateTo(agreementDateTo);
        assertEquals(expect, actualResult);
    }
}