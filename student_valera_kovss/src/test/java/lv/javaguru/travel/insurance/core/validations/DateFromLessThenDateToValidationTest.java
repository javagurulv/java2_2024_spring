package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.validation.DateFromAndDateToValidation;
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

class DateFromLessThenDateToValidationTest {
    private DateFromAndDateToValidation validation = new DateFromAndDateToValidation();

    @Test
    public void shouldDateFromIsAfterDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("05.01.2024"));
        Optional<ValidationError> errorOpt = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "agreementDateFrom");
        assertEquals(errorOpt.get().getMessage(), "Must be less then agreementDateTo!");
    }

    @Test
    public void shouldDateFromIsEqualsDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("04.01.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("04.01.2024"));
        Optional<ValidationError> errorOpt = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "agreementDateFrom");
        assertEquals(errorOpt.get().getMessage(), "Must be less then agreementDateTo!");
    }

    @Test
    public void shouldDateFromIsLessDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("04.01.2024"));
        when(request.getAgreementDateTo()).thenReturn(createDate("05.01.2024"));
        Optional<ValidationError> errorOpt = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOpt.isEmpty());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

