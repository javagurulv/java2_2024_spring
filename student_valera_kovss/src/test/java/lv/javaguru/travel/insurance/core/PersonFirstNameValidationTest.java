package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonFirstNameValidationTest {

    private PersonFirstNameValidation validation = new PersonFirstNameValidation();

    @Test
    public void shouldPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.validatePersonFirstName(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "personFirstName");
        assertEquals(errorOpt.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> errorOpt = validation.validatePersonFirstName(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "personFirstName");
        assertEquals(errorOpt.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Valera");
        Optional<ValidationError> errorOpt = validation.validatePersonFirstName(request);
        assertTrue(errorOpt.isEmpty());
    }

}

