package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatePersonLastNameTest {
    ValidatePersonLastName validate = new ValidatePersonLastName();

    @Test
    public void checkValidatorErrorResponseWhenRequestLastNameIsEmpty(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> error = validate.validatePersonLastName(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personLastName");
        assertEquals(error.get().getMessage(),"Must not be empty!");
    }

    @Test
    public void checkValidatorErrorResponseWhenRequestLastNameIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> error = validate.validatePersonLastName(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personLastName");
        assertEquals(error.get().getMessage(),"Must not be empty!");
    }

    @Test
    public void checkValidatorErrorResponseWhenRequestLastNameIsCorrect(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Kuznetsov");
        Optional<ValidationError> error = validate.validatePersonLastName(request);
        assertTrue(error.isEmpty());
    }
}
