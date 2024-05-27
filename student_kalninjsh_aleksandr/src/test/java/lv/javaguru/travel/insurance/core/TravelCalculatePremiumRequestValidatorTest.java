package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Mock
    private TravelCalculatePremiumRequest request;

    @Mock
    private DateTimeService service;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    public void returnErrorWhenPersonFirstNameIsNull() {
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void returnErrorWhenPersonFirstNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personFirstName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsPersonName() {
        when(request.getPersonFirstName()).thenReturn("Tom");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorWhenPersonLastNameIsNull() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void returnErrorWhenPersonLastNameIsEmpty() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "personLastName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsPersonLastName() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("Sawyer");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorWhenAgreementDateFromIsNull() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("Sawyer");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsAgreementDateFrom() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorWhenAgreementDateToIsNull() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(null);
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateTo");
        assertEquals(errors.get(0).getMessage(), "Must not be empty");
    }

    @Test
    public void notReturnErrorIfThereIsAgreementDateTo() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorWhenAgreementDateFromIsAfterAgreementDateTo() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.20"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.15"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must be less then agreementDateTo");
    }

    @Test
    public void returnErrorWhenAgreementDateFromIsInThePast() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2000.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2005.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Should not be in the past!");
    }

    @Test
    public void returnErrorWhenAgreementDateToIsInThePast() {
        when(request.getPersonFirstName()).thenReturn("personFirstName");
        when(request.getPersonLastName()).thenReturn("personLastName");
        when(request.getAgreementDateFrom()).thenReturn(createDate("2005.05.15"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2000.05.20"));
        when(service.currentDate()).thenReturn(createDate("2003.05.15"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(1).getField(), "agreementDateTo");
        assertEquals(errors.get(1).getMessage(), "Should not be in the past!");
    }

    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}