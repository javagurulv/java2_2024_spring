package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumRequestValidatorTest {


    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();




    @Test
    public void shouldTestFirstNameIfPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2000,0,1));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000,1,1));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }


    @Test
    public void shouldTestFirstNameIfNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Radigins");
        when(request.getAgreementDateFrom()).thenReturn(new Date(2000,0,1));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000,1,1));
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
        when(request.getAgreementDateFrom()).thenReturn(new Date(2000,0,1));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000,1,1));
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
        when(request.getAgreementDateFrom()).thenReturn(new Date(2000,0,1));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000,1,1));
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }


    @Test
    public void shouldTestLastNameIfNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn(null);
        when(request.getAgreementDateFrom()).thenReturn(new Date(2000,0,1));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000,1,1));
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
        when(request.getAgreementDateFrom()).thenReturn(new Date(2000,0,1));
        when(request.getAgreementDateTo()).thenReturn(new Date(2000,1,1));
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
        when(request.getAgreementDateTo()).thenReturn(new Date(2000,1,1));
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
        when(request.getAgreementDateFrom()).thenReturn(new Date(2000,0,1));
        when(request.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals("personDateTo",errors.get(0).getField());
        assertEquals("Must not be empty!",errors.get(0).getMessage());
    }

}