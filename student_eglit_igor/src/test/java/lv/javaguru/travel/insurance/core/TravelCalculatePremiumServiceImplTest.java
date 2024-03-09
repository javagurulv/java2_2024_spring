package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
            "Igor",
            "Eglit",
            LocalDate.of(2024, 12, 12),
            LocalDate.of(2024, 12, 13));

    private DateTimeService dateTimeService;
    private AgreementPriceCalculator agreementPriceCalculator;
    TravelCalculatePremiumServiceImpl service;
    TravelCalculatePremiumResponse response;

    @BeforeEach
    public void setUp() {
        dateTimeService = new DateTimeService();
        agreementPriceCalculator = new AgreementPriceCalculator(dateTimeService);
        service = new TravelCalculatePremiumServiceImpl(agreementPriceCalculator);
        response = service.calculatePremium(request);
    }
    @Test
    public void shouldCheckResponseNotNull() {
        assertNotNull(response);
    }

    @Test
    public void shouldCheckResponsePersonFirstName() {
        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void shouldCheckResponsePersonLastName() {
        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void shouldCheckResponseAgreementDateFrom() {
        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void shouldCheckResponseAgreementDateTo() {
        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void shouldCheckResponseAgreementPrice() {

        assertEquals(new BigDecimal(2), response.getAgreementPrice());
    }

}