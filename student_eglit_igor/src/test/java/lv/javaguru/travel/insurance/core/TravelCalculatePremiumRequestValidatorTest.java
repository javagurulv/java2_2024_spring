package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validation.*;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    TravelCalculatePremiumRequest request;
    @Mock
    private RequestValidationPersonFirstName requestValidator_personFirstName;
    @Mock
    private RequestValidationPersonLastName requestValidator_personLastName;
    @Mock
    private RequestValidationAgreementDateFrom requestValidation_agreementDateFrom;
    @Mock
    private RequestValidationAgreementDateTo requestValidation_agreementDateTo;
    @Mock
    private RequestValidationAgreementDateToIsAfterDateFrom requestValidation_agreementDateToIsAfterDateFrom;
    @Mock
    private RequestValidationAgreementDateFromNotInThePast requestValidator_agreementDateFromNotInThePast;
    @Mock
    private RequestValidationAgreementDateToNotInThePast requestValidator_agreementDateToNotInThePast;

    @InjectMocks
    TravelCalculatePremiumRequestValidator validator;



    @Test
    void validateNoErrorsWithAllFields() {
        when(requestValidator_personFirstName.validatePersonFirstName(request)).thenReturn(Optional.empty());
        when(requestValidator_personLastName.validatePersonLastName(request)).thenReturn(Optional.empty());
        when(requestValidation_agreementDateFrom.validateAgreementDateFrom(request)).thenReturn(Optional.empty());
        when(requestValidation_agreementDateTo.validateAgreementDateTo(request)).thenReturn(Optional.empty());
        when(requestValidation_agreementDateToIsAfterDateFrom.validateAgreementDateToIsAfterDateFrom(request)).thenReturn(Optional.empty());
        when(requestValidator_agreementDateFromNotInThePast.validateAgreementDateFromNotInThePast(request)).thenReturn(Optional.empty());
        when(requestValidator_agreementDateToNotInThePast.validateAgreementDateToNotInThePast(request)).thenReturn(Optional.empty());
        assertEquals(0, validator.validate(request).size());
    }
    @Test
    public void shouldReturnError() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(requestValidator_personFirstName.validatePersonFirstName(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestValidator_personLastName.validatePersonLastName(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestValidation_agreementDateFrom.validateAgreementDateFrom(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestValidation_agreementDateTo.validateAgreementDateTo(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestValidation_agreementDateToIsAfterDateFrom.validateAgreementDateToIsAfterDateFrom(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestValidator_agreementDateFromNotInThePast.validateAgreementDateFromNotInThePast(request)).thenReturn(Optional.of(new ValidationError()));
        when(requestValidator_agreementDateToNotInThePast.validateAgreementDateToNotInThePast(request)).thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 7);
    }


   /* @Test
    void validateAgreementDateIsAfterDateFrom() {
        request.setAgreementDateTo(LocalDate.of(2024, 12, 11));
        assertEquals(1, validator.validate(request).size(), "Date To is before Date From");
        assertEquals("agreementDateTo", validator.validate(request).get(0).getField());
        assertEquals("agreementDateTo must be after agreementDateFrom!", validator.validate(request).get(0).getMessage());
    }*/
}