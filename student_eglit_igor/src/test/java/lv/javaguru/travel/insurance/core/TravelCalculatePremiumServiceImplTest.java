package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
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

    private final DateTimeService dateTimeService = new DateTimeService();
    private final AgreementPriceCalculator agreementPriceCalculator = new AgreementPriceCalculator(dateTimeService);
    TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl(agreementPriceCalculator);
    TravelCalculatePremiumResponse response = service.calculatePremium(request);

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