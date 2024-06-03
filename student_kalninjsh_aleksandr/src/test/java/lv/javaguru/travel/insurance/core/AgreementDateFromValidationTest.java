package lv.javaguru.travel.insurance.core;

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

class AgreementDateFromValidationTest {

    private AgreementDateFromValidation validation = new AgreementDateFromValidation();

    @Test
    public void returnErrorWhenAgreementDateFromIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> error = validation.validateAgreementDateFrom(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateFrom");
        assertEquals(error.get().getMessage(), "Must not be empty!");
    }

    @Test
    public void notReturnErrorIfThereIsAgreementDateFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2025.01.01"));
        Optional<ValidationError> error = validation.validateAgreementDateFrom(request);
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
