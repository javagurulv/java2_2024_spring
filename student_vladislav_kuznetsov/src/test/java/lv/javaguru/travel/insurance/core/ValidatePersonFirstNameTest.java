package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatePersonFirstNameTest {
    ValidatePersonFirstName validate = new ValidatePersonFirstName();

    @Test
    public void checkValidatorErrorResponseWhenRequestFirstNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> error = validate.validatePersonFirstName(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personFirstName");
        assertEquals(error.get().getMessage(),"Must not be empty!");
    }
    @Test
    public void checkValidatorErrorResponseWhenRequestFirstNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> error = validate.validatePersonFirstName(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personFirstName");
        assertEquals(error.get().getMessage(),"Must not be empty!");
    }

    @Test
    public void checkValidatorErrorResponseWhenRequestFirstNameIsCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vladislav");
        Optional<ValidationError> error = validate.validatePersonFirstName(request);
        assertTrue(error.isEmpty());
    }
}
