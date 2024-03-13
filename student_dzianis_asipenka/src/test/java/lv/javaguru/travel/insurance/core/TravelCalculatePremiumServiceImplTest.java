package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {
    @Mock
    DateTimeService dateTimeService;
    @InjectMocks
    TravelCalculatePremiumServiceImpl calculatePremiumService;

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
        when(dateTimeService.differenceBetweenDates(request.getAgreementDateTo(), request.getAgreementDateFrom())).thenReturn(new BigDecimal(0));
        TravelCalculatePremiumResponse actualResult = calculatePremiumService.calculatePremium(request);
        long diffInMillies = Math.abs(request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime());
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        assertEquals(BigDecimal.valueOf(daysBetween), actualResult.getAgreementPrice());
    }
}