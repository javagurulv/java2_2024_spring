package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonFirstNameValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private PersonFirstNameValidator validation;

    @Test
    void personFirstNameDoNotExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(1)).thenReturn(validationError);
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertSame(errors.get(), validationError);
    }

    @Test
    void personFirstNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(1)).thenReturn(validationError);
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertSame(errors.get(), validationError);
    }

    @Test
    void personFirstNameIsExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("Vladislav");
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isEmpty());
    }

}
