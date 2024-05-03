package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.services.TravelCalculatePremiumServiceImpl;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import lv.javaguru.travel.insurance.core.validation.TravelCalculatePremiumRequestValidatorInterface;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelCalculatePremiumRequestValidatorInterface requestValidator;
    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPremiumResponseWhenNoValidationErrors() {
        when(requestValidator.validate(any(TravelCalculatePremiumRequestV1.class))).thenReturn(Collections.emptyList());
        when(premiumUnderwriting.calculateAgreementPremium(any(TravelCalculatePremiumRequestV1.class))).thenReturn(new TravelPremiumCalculationResult(BigDecimal.ZERO, Collections.emptyList()));

        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);

        assertNotNull(response);
        assertEquals(BigDecimal.ZERO, response.getAgreementPremium());
        assertTrue(response.getRiskPremiums().isEmpty());
    }

    @Test
    void shouldReturnErrorResponseWhenValidationErrorsExist() {
        List<ValidationError> errors = List.of(new ValidationError("field", "error message"));
        when(requestValidator.validate(any(TravelCalculatePremiumRequestV1.class))).thenReturn(errors);

        TravelCalculatePremiumRequestV1 request = new TravelCalculatePremiumRequestV1();
        TravelCalculatePremiumResponseV1 response = service.calculatePremium(request);

        assertNotNull(response);
        assertTrue(response.hasErrors());
        assertEquals(errors, response.getErrors());
    }
}