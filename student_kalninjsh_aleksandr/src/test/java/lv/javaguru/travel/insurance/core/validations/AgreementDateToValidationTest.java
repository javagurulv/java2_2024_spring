package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgreementDateToValidationTest {

    private AgreementDateToValidation validation = new AgreementDateToValidation();


    @Test
    public void returnErrorWhenAgreementDateToIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateAgreementDateTo(request);
        assertEquals(error.get().getField(), "agreementDateTo");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void notReturnErrorIfThereIsAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("2025.01.10"));
        Optional<ValidationError> error = validation.validateAgreementDateTo(request);
        assertTrue(error.isEmpty());
    }

    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
