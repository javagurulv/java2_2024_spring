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
public class ValidateAgreementDateToTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private ValidateAgreementDateTo validation;

    @Test
    public void checkThatErrorIsPresentWhenAgreementDateToIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        NullPointerException exception = new NullPointerException();
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_5")).thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertNull(exception.getMessage());
        assertSame(error.get(), validationError);
    }

    @Test
    public void checkThatNoErrorIsPresentWhenAgreementDateToIsNotNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date());
        Optional<ValidationError> error = validation.validate(request);
        assertFalse(error.isPresent());
    }
}
