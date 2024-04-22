package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }


    @Test
    public void shouldTestFirstNameIfNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(request.getPersonLastName()).thenReturn("Radigins");
        List<ValidationError> errors = requestValidator.validate(request);
        ValidationError validationError = new ValidationError();
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals(errors.get(0).getField(),"personFirstName");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldTestFirstNameIfEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(request.getPersonLastName()).thenReturn("Radigins");
        List<ValidationError> errors = requestValidator.validate(request);
        ValidationError validationError = new ValidationError();
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals(errors.get(0).getField(),"personFirstName");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }


    @Test
    public void shouldTestLastNameIfPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("Radigins");
        List<ValidationError> errors = requestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }


    @Test
    public void shouldTestLastNameIfNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(request);
        ValidationError validationError = new ValidationError();
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals(errors.get(0).getField(),"personLastName");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }

    @Test
    public void shouldTestLastNameIfEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Olegs");
        when(request.getPersonLastName()).thenReturn("");
        List<ValidationError> errors = requestValidator.validate(request);
        ValidationError validationError = new ValidationError();
        assertFalse(errors.isEmpty());
        assertEquals(1,errors.size());
        assertEquals(errors.get(0).getField(),"personLastName");
        assertEquals(errors.get(0).getMessage(),"Must not be empty!");
    }
}