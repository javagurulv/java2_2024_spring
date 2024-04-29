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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ValidateAgreementDateToIsNotBeforeCurrentDateTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private ValidateAgreementDateToIsNotBeforeCurrentDate validation;
    @Test
    public void checkIfErrorMessageIsPresentWhenAgreementDateToIsInThePast(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date(0));
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void checkIfNoErrorMessageIsPresentWhenAgreementDateToIsInTheFuture(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date(System.currentTimeMillis()+60000));
        Optional<ValidationError> error = validation.validate(request);
        assertFalse(error.isPresent());
    }

    @Test
    public void checkIfNoErrorIsPresentWhenAgreementDateFromIsCurrentTime(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date(System.currentTimeMillis()+60));
        Optional<ValidationError> error = validation.validate(request);
        assertFalse(error.isPresent());
    }
}
