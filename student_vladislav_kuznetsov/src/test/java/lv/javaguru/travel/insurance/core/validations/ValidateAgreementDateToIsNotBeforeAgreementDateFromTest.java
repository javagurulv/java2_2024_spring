package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ValidateAgreementDateToIsNotBeforeAgreementDateFrom;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateAgreementDateToIsNotBeforeAgreementDateFromTest {
    ValidateAgreementDateToIsNotBeforeAgreementDateFrom validate = new ValidateAgreementDateToIsNotBeforeAgreementDateFrom();

    @Test
    public void checkThatErrorIsPresentWhenAgreementDateFromAndAgreementDateToIsNull(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        when(request.getAgreementDateTo()).thenReturn(null);
        NullPointerException exception = new NullPointerException();
        Optional<ValidationError> error = validate.validateAgreementDateToIsNotBeforeAgreementDateFrom(request);
        assertNull(exception.getMessage());
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo and agreementDateTo");
        assertEquals(error.get().getMessage(),"Must not be empty!");
    }

    @Test
    public void checkIfErrorIsPresentWhenAgreementDateToEqualsAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        Date date = new Date();
        when(request.getAgreementDateFrom()).thenReturn(date);
        when(request.getAgreementDateTo()).thenReturn(date);
        Optional<ValidationError> error = validate.validateAgreementDateToIsNotBeforeAgreementDateFrom(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(),"Invalid date !");
    }

    @Test
    public void checkIfErrorIsPresentWhenAgreementDateToIsBeforeAgreementDateFrom(){
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(new Date(System.currentTimeMillis()+60000));
        when(request.getAgreementDateTo()).thenReturn(new Date());
        Optional<ValidationError> error = validate.validateAgreementDateToIsNotBeforeAgreementDateFrom(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(),"Invalid date !");
    }

}
