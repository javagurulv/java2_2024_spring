package lv.javaguru.travel.insurance.core.validations;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateAgreementDateToTest {

    ValidateAgreementDateTo validate = new ValidateAgreementDateTo();

    @Test
    public void checkThatErrorIsPresentWhenAgreementDateToIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        NullPointerException exception = new NullPointerException();
        Optional<ValidationError> error = validate.execute(request);
        assertNull(exception.getMessage());
        assertEquals(error.get().getErrorCode(), "agreementDateTo");
        assertEquals(error.get().getDescription(),"Must not be empty!");
    }

    @Test
    public void checkThatNoErrorIsPresentWhenAgreementDateToIsNotNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date());
        Optional<ValidationError> error = validate.execute(request);
        assertFalse(error.isPresent());
    }
}
