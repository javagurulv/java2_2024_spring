package lv.javaguru.travel.insurance.core;


import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.core.underwriting.AgreementPriceCalculatorInterface;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidatorInterface;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(
            "Igor",
            "Eglit",
            LocalDate.of(2024, 12, 12),
            LocalDate.of(2024, 12, 13),
            List.of("risk1", "risk2"));

    @Mock
    private AgreementPriceCalculatorInterface mockAgreementPriceCalculator;
    @Mock
    private TravelCalculatePremiumRequestValidatorInterface validator;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    TravelCalculatePremiumResponse response;

    @BeforeEach
    public void setUp() {
        when(mockAgreementPriceCalculator.calculateAgreementPrice(request))
                .thenReturn(new BigDecimal(2));
        when(validator.validate(request)).thenReturn(null);
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