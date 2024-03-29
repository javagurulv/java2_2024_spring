package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    void validateTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2024, 3, 18));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2024, 3, 8));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 3);
    }

    @Test
    void validatePersonFirstNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Romanov");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2024, 3, 17));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2024, 3, 18));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "must exist and not to be empty!");
    }

    @Test
    void validatePersonLastNameTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vladislav");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2024, 3, 17));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2024, 3, 18));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "must exist and not to be empty!");
    }

    @Test
    void validateDateFromTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vladislav");
        when(request.getPersonLastName()).thenReturn("Romanov");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2024, 3, 18));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "must exist and not to be empty!");
        assertEquals(errors.get(1).getField(), "Travel Period");
        assertEquals(errors.get(1).getMessage(), "contain incorrect data!");
    }

    @Test
    void validateDateToTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vladislav");
        when(request.getPersonLastName()).thenReturn("Romanov");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2024, 3, 18));
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "must exist and not to be empty!");
        assertEquals(errors.get(1).getField(), "Travel Period");
        assertEquals(errors.get(1).getMessage(), "contain incorrect data!");
    }

    @Test
    void validateTravelPeriodTest() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vladislav");
        when(request.getPersonLastName()).thenReturn("Romanov");
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2024, 3, 18));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2024, 3, 8));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Travel Period");
        assertEquals(errors.get(0).getMessage(), "contain incorrect data!");
    }



}
