package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateToInFutureValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private AgreementDateToInFutureValidator validation;

    @Test
    void dateToIsInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2019, 3, 31));
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(6)).thenReturn(validationError);
        Optional<ValidationError> errors = validation.validate(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get(), validationError);
    }
    @Test
    void dateToIsNotInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2030, 3, 31));
        Optional<ValidationError> errors = validation.validate(request);
        assertTrue(errors.isEmpty());
    }

}
