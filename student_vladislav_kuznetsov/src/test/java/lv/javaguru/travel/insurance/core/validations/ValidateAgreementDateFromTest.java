package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidateAgreementDateFromTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private ValidateAgreementDateFrom validation;

    @Test
    public void checkThatErrorIsPresentWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        NullPointerException exception = new NullPointerException();
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_3")).thenReturn(validationError);
        Optional<ValidationError> error = validation.execute(request);
        assertNull(exception.getMessage());
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void checkThatNoErrorIsPresentWhenAgreementDateFromIsNotNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        Optional<ValidationError> error = validation.execute(request);
        assertFalse(error.isPresent());
    }
}
