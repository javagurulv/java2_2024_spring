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
public class ValidateAgreementDateToIsNotBeforeAgreementDateFromTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private ValidateAgreementDateToIsNotBeforeAgreementDateFrom validation;

    @Test
    public void checkIfErrorIsPresentWhenAgreementDateToEqualsAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        Date date = new Date();
        when(request.getAgreementDateFrom()).thenReturn(date);
        when(request.getAgreementDateTo()).thenReturn(date);
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }

    @Test
    public void checkIfErrorIsPresentWhenAgreementDateToIsBeforeAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(System.currentTimeMillis()+60000));
        when(request.getAgreementDateTo()).thenReturn(new Date());
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_6")).thenReturn(validationError);
        Optional<ValidationError> error = validation.validate(request);
        assertTrue(error.isPresent());
        assertSame(error.get(), validationError);
    }
}
