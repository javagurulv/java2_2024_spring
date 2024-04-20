package lv.javaguru.travel.insurance.core.validations;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateAgreementDateFromIsNotBeforeCurrentDateTest {
    ValidateAgreementDateFromIsNotBeforeCurrentDate validate = new ValidateAgreementDateFromIsNotBeforeCurrentDate();
    @Test
    public void checkIfErrorMessageIsPresentWhenAgreementDateFromIsInThePast(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(0));
        Optional<ValidationError> error = validate.execute(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getErrorCode(), "agreementDateFrom");
        assertEquals(error.get().getDescription(),"Invalid date !");
    }

    @Test
    public void checkIfNoErrorMessageIsPresentWhenAgreementDateFromIsInTheFuture(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(System.currentTimeMillis()+60000));
        Optional<ValidationError> error = validate.execute(request);
        assertFalse(error.isPresent());
    }

    @Test
    public void checkIfNoErrorIsPresentWhenAgreementDateFromIsCurrentTime(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(System.currentTimeMillis()+60));
        Optional<ValidationError> error = validate.execute(request);
        assertFalse(error.isPresent());
    }
}
