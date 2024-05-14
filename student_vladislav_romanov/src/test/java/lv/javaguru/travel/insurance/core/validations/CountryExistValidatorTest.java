package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryExistValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private CountryExistValidator validation;

    @Test
    void countryDoNotExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(10)).thenReturn(validationError);
        Optional<ValidationError> errors = validation.validate(request);
        assertTrue(errors.isPresent());
        assertSame(errors.get(), validationError);
    }

    @Test
    void countryIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("");
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(10)).thenReturn(validationError);
        Optional<ValidationError> errors = validation.validate(request);
        assertTrue(errors.isPresent());
        assertSame(errors.get(), validationError);
    }

    @Test
    void countryIsExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getCountry()).thenReturn("GREECE");
        Optional<ValidationError> errors = validation.validate(request);
        assertTrue(errors.isEmpty());
    }

}
