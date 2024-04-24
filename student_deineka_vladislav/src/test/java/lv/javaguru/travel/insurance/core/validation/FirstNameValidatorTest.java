package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FirstNameValidatorTest {

    private FirstNameValidator firstNameValidator = new FirstNameValidator();

    @Test
    public void errorIfFirstNameIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn(null);
        Optional<ValidationErrors> errorsOptional = firstNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "firstName");
        assertEquals(errorsOptional.get().getMessage(), "Field cannot be empty");
    }

    @Test
    public void errorIfFirstNameIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("");
        Optional<ValidationErrors> errorsOptional = firstNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "firstName");
        assertEquals(errorsOptional.get().getMessage(), "Field cannot be empty");
    }

    @Test
    public void noErrorIfFirstNameIsPresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("Bob");
        Optional<ValidationErrors> errorsOptional = firstNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
    }

}
