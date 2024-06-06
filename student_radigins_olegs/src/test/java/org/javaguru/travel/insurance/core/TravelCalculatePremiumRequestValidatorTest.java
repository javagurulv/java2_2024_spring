package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {


    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculatePremiumRequestValidator requestValidator;



    @Test
    public void shouldTestFirstNameIfPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.1.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }


    @Test
    public void shouldTestFirstNameIfNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals("personFirstName",errors.get(0).getField());
        assertEquals("Must not be empty!",errors.get(0).getMessage());
    }

    @Test
    public void shouldTestFirstNameIfEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.1.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals("personFirstName",errors.get(0).getField());
        assertEquals("Must not be empty!",errors.get(0).getMessage());
    }


    @Test
    public void shouldTestLastNameIfPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.1.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }


    @Test
    public void shouldTestLastNameIfNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.1.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals("personLastName",errors.get(0).getField());
        assertEquals("Must not be empty!",errors.get(0).getMessage());
    }

    @Test
    public void shouldTestLastNameIfEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.1.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals("personLastName",errors.get(0).getField());
        assertEquals("Must not be empty!",errors.get(0).getMessage());
    }

    @Test
    public void shouldTestDateFromIfNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(createDate("10.1.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals("personDateFrom",errors.get(0).getField());
        assertEquals("Must not be empty!",errors.get(0).getMessage());
    }

    @Test
    public void shouldTestDateToIfNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(null);
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals("personDateTo",errors.get(0).getField());
        assertEquals("Must not be empty!",errors.get(0).getMessage());
    }


    @Test
    public void shouldTestCorrectDateNegative() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.1.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals("agreement date to",errors.get(0).getField());
        assertEquals("Must be after!",errors.get(0).getMessage());
    }

    @Test
    public void shouldTestCorrectDatePositive() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.1.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());

    }

    @Test
    public void shouldReturnErrorWhenAgreementDateFromInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2020"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2025"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "Must be in the future!");
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2020"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(1).getField(), "agreementDateTo");
        assertEquals(errors.get(1).getMessage(), "Must be in the future!");
    }
    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}