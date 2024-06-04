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

class AgreementDateFromLessThenAgreementDateToValidationTest {

    private AgreementDateFromLessThenAgreementDateToValidation validation = new AgreementDateFromLessThenAgreementDateToValidation();

    @Test
    public void returnErrorWhenAgreementDateFromIsAfterAgreementDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("2025.01.10"));
        when(request.getAgreementDateTo()).thenReturn(createDate("2025.01.01"));
        Optional<ValidationError> error = validation.validateAgreementDateFromLessThenAgreementDateTo(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "agreementDateFrom");
        assertEquals(error.get().getMessage(), "Must be less then agreementDateTo!");
    }

    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
