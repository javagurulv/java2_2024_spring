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
public class ValidateAgreementDateFromIsNotBeforeCurrentDateTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private ValidateAgreementDateFromIsNotBeforeCurrentDate validation;
    @Test
    public void checkIfErrorMessageIsPresentWhenAgreementDateFromIsInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(0));
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_4")).thenReturn(validationError);
        Optional<ValidationError> error = validation.execute(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void checkIfNoErrorMessageIsPresentWhenAgreementDateFromIsInTheFuture(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(System.currentTimeMillis()+60000));
        Optional<ValidationError> error = validation.execute(request);
        assertFalse(error.isPresent());
    }

    @Test
    public void checkIfNoErrorIsPresentWhenAgreementDateFromIsCurrentTime(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(System.currentTimeMillis()+60));
        Optional<ValidationError> error = validation.execute(request);
        assertFalse(error.isPresent());
    }
}
