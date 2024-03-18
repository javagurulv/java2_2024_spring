package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumRequest request;
    private TravelCalculatePremiumServiceImpl calculate;

    @BeforeEach
    private void setUp() {
        DateTimeService dateTimeService = new DateTimeService();
        calculate = new TravelCalculatePremiumServiceImpl(dateTimeService);

        request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Jānis");
        request.setPersonLastName("Bērziņš");
        request.setAgreementDateFrom(new Date(2024 - 1900, 2, 10)); // March 10, 2024
        request.setAgreementDateTo(new Date(2024 - 1900, 2, 11)); // March 11, 2024
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectPersonFirstName() {
        TravelCalculatePremiumResponse response = calculate.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectPersonLastName() {
        TravelCalculatePremiumResponse response = calculate.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectAgreementDateFrom() {
        TravelCalculatePremiumResponse response = calculate.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void calculatePremium_ShouldReturnCorrectAgreementDateTo() {
        TravelCalculatePremiumResponse response = calculate.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

}