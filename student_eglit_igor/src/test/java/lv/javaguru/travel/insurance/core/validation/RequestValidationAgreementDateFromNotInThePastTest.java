package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidationAgreementDateFromNotInThePastTest {
    private RequestValidationAgreementDateFrom requestValidationAgreementDateFrom;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    void setUp() {
        requestValidationAgreementDateFrom = new RequestValidationAgreementDateFrom();
        request = new TravelCalculatePremiumRequest();
    }

    @Test
    void shouldReturnValidationErrorWhenAgreementDateFromIsNull() {
        request.setAgreementDateFrom(null);
        Optional<ValidationError> result = requestValidationAgreementDateFrom.executeValidation(request);
        assertTrue(result.isPresent());
        assertEquals("agreementDateFrom", result.get().getField());
        assertEquals("Must not be empty!", result.get().getMessage());
    }

    @Test
    void shouldReturnEmptyWhenAgreementDateFromIsNotNull() {
        request.setAgreementDateFrom(LocalDate.now());
        Optional<ValidationError> result = requestValidationAgreementDateFrom.executeValidation(request);
        assertTrue(result.isEmpty());
    }

}