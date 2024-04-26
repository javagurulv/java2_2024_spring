package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    public void returnErrorWhenPersonFirstNameIsNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void returnErrorWhenPersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsPersonName() {
        when(request.getPersonFirstName()).thenReturn("Tom");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorWhenPersonLastNameIsNull() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void returnErrorWhenPersonLastNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsPersonLastName() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("Sawyer");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorWhenAgreementDateFromIsNull() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("Sawyer");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsAgreementDateFrom() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorWhenAgreementDateToIsNull() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsAgreementDateTo() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        when(request.getAgreementDateTo()).thenReturn(new Date());
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorWhenAgreementDateFromIsAfterAgreementDateTo() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2005, 05, 20));
        when(request.getAgreementDateTo()).thenReturn(new Date(2005, 05, 15));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must be less then agreementDateTo");
    }

}