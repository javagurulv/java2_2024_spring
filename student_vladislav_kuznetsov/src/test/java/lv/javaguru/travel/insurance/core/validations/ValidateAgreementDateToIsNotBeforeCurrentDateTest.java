package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ValidateAgreementDateToIsNotBeforeCurrentDate;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateAgreementDateToIsNotBeforeCurrentDateTest {
    ValidateAgreementDateToIsNotBeforeCurrentDate validate = new ValidateAgreementDateToIsNotBeforeCurrentDate();
    @Test
    public void checkIfErrorMessageIsPresentWhenAgreementDateToIsInThePast(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date(0));
        Optional<ValidationError> error = validate.validateAgreementDateToIsNotBeforeCurrentDate(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(),"Invalid date !");
    }

    @Test
    public void checkIfNoErrorMessageIsPresentWhenAgreementDateToIsInTheFuture(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date(System.currentTimeMillis()+60000));
        Optional<ValidationError> error = validate.validateAgreementDateToIsNotBeforeCurrentDate(request);
        assertFalse(error.isPresent());
    }

    @Test
    public void checkIfNoErrorIsPresentWhenAgreementDateFromIsCurrentTime(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(new Date(System.currentTimeMillis()+60));
        Optional<ValidationError> error = validate.validateAgreementDateToIsNotBeforeCurrentDate(request);
        assertFalse(error.isPresent());
    }
}
