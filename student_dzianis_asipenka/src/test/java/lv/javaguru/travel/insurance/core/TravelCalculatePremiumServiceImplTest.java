package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {
    DateTimeService dateTimeService = new DateTimeService();
    TravelCalculatePremiumServiceImpl calculatePremiumService = new TravelCalculatePremiumServiceImpl(dateTimeService);

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

    @Test
    public void expectTrueAgreementPrice() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Petrov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse actualResult = calculatePremiumService.calculatePremium(request);
        long diffInMillies = Math.abs(request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime());
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println(daysBetween);
        assertEquals(BigDecimal.valueOf(daysBetween), actualResult.getAgreementPrice());
    }
}