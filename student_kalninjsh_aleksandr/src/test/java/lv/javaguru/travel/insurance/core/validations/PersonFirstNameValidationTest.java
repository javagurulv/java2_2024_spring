package lv.javaguru.travel.insurance.core.validations;

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
    public void returnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> error = validation.validatePersonFirstName(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personFirstName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void returnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> error = validation.validatePersonFirstName(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personFirstName");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void notReturnErrorIfThereIsPersonName() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Tom");
        Optional<ValidationError> error = validation.validatePersonFirstName(request);
        assertTrue(error.isEmpty());
    }
}
