package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FirstNameValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private FirstNameValidator firstNameValidator;


    @Test
    public void errorIfFirstNameIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn(null);
        ValidationErrors validationErrors = mock(ValidationErrors.class);
        when(validationErrorFactory.createError("ERROR_CODE_2")).thenReturn(validationErrors);
        Optional<ValidationErrors> errorsOptional = firstNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertSame(errorsOptional.get(), validationErrors);
    }

    @Test
    public void errorIfFirstNameIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("");
        ValidationErrors validationErrors = mock(ValidationErrors.class);
        when(validationErrorFactory.createError("ERROR_CODE_2")).thenReturn(validationErrors);
        Optional<ValidationErrors> errorsOptional = firstNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertSame(errorsOptional.get(), validationErrors);
    }


    @Test
    public void noErrorIfFirstNameIsPresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonFirstName()).thenReturn("Bob");
        Optional<ValidationErrors> errorsOptional = firstNameValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
        verifyNoInteractions(validationErrorFactory);
    }

}
