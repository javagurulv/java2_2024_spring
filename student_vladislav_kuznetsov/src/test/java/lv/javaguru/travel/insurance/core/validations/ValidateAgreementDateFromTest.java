package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateAgreementDateFromTest {
    ValidateAgreementDateFrom validate = new ValidateAgreementDateFrom();

    @Test
    public void checkThatErrorIsPresentWhenAgreementDateFromIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        NullPointerException exception = new NullPointerException();
        Optional<ValidationError> error = validate.execute(request);
        assertNull(exception.getMessage());
        assertEquals(error.get().getField(), "agreementDateFrom");
        assertEquals(error.get().getMessage(),"Must not be empty!");
    }

    @Test
    public void checkThatNoErrorIsPresentWhenAgreementDateFromIsNotNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date());
        Optional<ValidationError> error = validate.execute(request);
        assertFalse(error.isPresent());
    }
}
