package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.validations.*;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private PersonFirstNameValidation personFirstNameValidation;
    @Mock
    private PersonLastNameValidation personLastNameValidation;
    @Mock
    private AgreementDateFromValidation agreementDateFromValidation;
    @Mock
    private AgreementDateToValidation agreementDateToValidation;
    @Mock
    private AgreementDateFromLessThenAgreementDateToValidation agreementDateFromLessThenAgreementDateToValidation;
    @Mock
    private AgreementDateFromNotInThePastValidation agreementDateFromNotInThePastValidation;
    @Mock
    private AgreementDateToNotInThePastValidation agreementDateToNotInThePastValidation;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    public void validationSucceed() {
        when(personLastNameValidation.validatePersonLastName(request)).thenReturn(Optional.empty());
        when(personLastNameValidation.validatePersonLastName(request)).thenReturn(Optional.empty());
        when(agreementDateFromValidation.validateAgreementDateFrom(request)).thenReturn(Optional.empty());
        when(agreementDateToValidation.validateAgreementDateTo(request)).thenReturn(Optional.empty());
        when(agreementDateFromLessThenAgreementDateToValidation.validateAgreementDateFromLessThenAgreementDateTo(request)).thenReturn(Optional.empty());
        when(agreementDateFromNotInThePastValidation.validateAgreementDateFromNotInThePast(request)).thenReturn(Optional.empty());
        when(agreementDateToNotInThePastValidation.validateAgreementDateToNotInThePast(request)).thenReturn(Optional.empty());
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnError() {
        when(personFirstNameValidation.validatePersonFirstName(request)).thenReturn(Optional.of(new ValidationError()));
        when(personLastNameValidation.validatePersonLastName(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateFromValidation.validateAgreementDateFrom(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateToValidation.validateAgreementDateTo(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateFromLessThenAgreementDateToValidation.validateAgreementDateFromLessThenAgreementDateTo(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateFromNotInThePastValidation.validateAgreementDateFromNotInThePast(request)).thenReturn(Optional.of(new ValidationError()));
        when(agreementDateToNotInThePastValidation.validateAgreementDateToNotInThePast(request)).thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 7);
    }
}