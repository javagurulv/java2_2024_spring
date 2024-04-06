package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidationAgreementDateToIsAfterDateFromTest {
private RequestValidationAgreementDateToIsAfterDateFrom requestValidationAgreementDateToIsAfterDateFrom;
private TravelCalculatePremiumRequest travelCalculatePremiumRequest;
    @BeforeEach
    void setUp() {
        requestValidationAgreementDateToIsAfterDateFrom = new RequestValidationAgreementDateToIsAfterDateFrom();
        travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
    }
    @Test
    void shouldReturnErrorWhenAgreementDateToIsBeforeAgreementDateFrom(){
        travelCalculatePremiumRequest.setAgreementDateTo(LocalDate.now().minusDays(1));
        travelCalculatePremiumRequest.setAgreementDateFrom(LocalDate.now().plusDays(1));
        Optional<ValidationError> error = requestValidationAgreementDateToIsAfterDateFrom.executeValidation(travelCalculatePremiumRequest);
        assertTrue(error.isPresent());
        assertEquals("agreementDateTo", error.get().getField());
        assertEquals("agreementDateTo must be after agreementDateFrom!", error.get().getMessage());
    }
    @Test
    void shouldReturnEmptyWhenAgreementDateToIsAfterAgreementDateFrom(){
        travelCalculatePremiumRequest.setAgreementDateTo(LocalDate.now().plusDays(3));
        travelCalculatePremiumRequest.setAgreementDateFrom((LocalDate.now().plusDays(1)));
        Optional<ValidationError> error = requestValidationAgreementDateToIsAfterDateFrom.executeValidation(travelCalculatePremiumRequest);
        assertTrue(error.isEmpty());
    }
}