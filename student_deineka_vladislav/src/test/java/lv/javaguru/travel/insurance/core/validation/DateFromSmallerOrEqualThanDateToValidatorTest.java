package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateFromSmallerOrEqualThanDateToValidatorTest {

    private DateFromSmallerThanDateToValidator dateFromSmallerThanDateToValidator = new DateFromSmallerThanDateToValidator();

    @Test
    public void errorIfDateToIsEqualDateFrom() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(createNewDate("10.10.2030"));
        when(premiumRequest.getAgreementDateTo()).thenReturn(createNewDate("10.10.2030"));
        Optional<ValidationErrors> errorsOptional = dateFromSmallerThanDateToValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "dateFrom");
        assertEquals(errorsOptional.get().getMessage(), "Cannot be bigger or equal to dateTo");
    }

    @Test
    public void errorIfDateFromIsBiggerThanDateTo() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(createNewDate("10.10.2050"));
        when(premiumRequest.getAgreementDateTo()).thenReturn(createNewDate("10.10.2030"));
        Optional<ValidationErrors> errorsOptional = dateFromSmallerThanDateToValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "dateFrom");
        assertEquals(errorsOptional.get().getMessage(), "Cannot be bigger or equal to dateTo");
    }

    private Date createNewDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
