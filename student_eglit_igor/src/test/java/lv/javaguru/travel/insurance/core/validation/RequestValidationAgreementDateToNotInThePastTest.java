package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestValidationAgreementDateToNotInThePastTest {
    private RequestValidationAgreementDateToNotInThePast requestValidationAgreementDateToNotInThePast;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        requestValidationAgreementDateToNotInThePast = new RequestValidationAgreementDateToNotInThePast();
        request = new TravelCalculatePremiumRequest();
    }

    @Test
    void shouldReturnValidationErrorWhenAgreementDateToIsInThePast() {
        request.setAgreementDateFrom(LocalDate.now().minusDays(5));
        request.setAgreementDateTo(LocalDate.now().minusDays(3));
        Optional<ValidationError> result = requestValidationAgreementDateToNotInThePast.executeValidation(request);
        assertTrue(result.isPresent());
        assertEquals("agreementDateTo", result.get().getField());
        assertEquals("Must not be in the past!", result.get().getMessage());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateToIsNotInThePast() {
        request.setAgreementDateTo(LocalDate.now().plusDays(1));
        Optional<ValidationError> result = requestValidationAgreementDateToNotInThePast.executeValidation(request);
        assertTrue(result.isEmpty());
    }
}