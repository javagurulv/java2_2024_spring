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
public class AgreementDateToExistValidatorTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private AgreementDateToExistValidator validation;

    @Test
    void dateToIsNotExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        ValidationError validationError = mock(ValidationError.class);
        when(validationErrorFactory.buildError(5)).thenReturn(validationError);
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get(), validationError);

    }

    @Test
    void dateToIsExist() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2030, 3, 31));
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isEmpty());
    }
}
