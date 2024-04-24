package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LastNameValidatorTest {

    private LastNameValidator lastNameValidator = new LastNameValidator();

    @Test
    public void errorIfLastNameIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonLastName()).thenReturn(null);
        Optional<ValidationErrors> errorsOptional = lastNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "lastName");
        assertEquals(errorsOptional.get().getMessage(), "Field cannot be empty");
    }

    @Test
    public void errorIfLastNameIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonLastName()).thenReturn("");
        Optional<ValidationErrors> errorsOptional = lastNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "lastName");
        assertEquals(errorsOptional.get().getMessage(), "Field cannot be empty");
    }

    @Test
    public void noErrorIfLastNameIsPresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonLastName()).thenReturn("Johnson");
        Optional<ValidationErrors> errorsOptional = lastNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
    }

}

