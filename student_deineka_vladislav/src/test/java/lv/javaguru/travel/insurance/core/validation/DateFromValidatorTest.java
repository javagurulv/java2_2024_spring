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
class DateFromValidatorTest {

    private DateFromValidator dateFromValidator = new DateFromValidator();

    @Test
    public void errorIfDateFromIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationErrors> errorsOptional = dateFromValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "dateFrom");
        assertEquals(errorsOptional.get().getMessage(), "Field cannot be empty");
    }

    @Test
    public void noErrorIfDateFromIsPresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(createNewDate("10.10.2030"));
        Optional<ValidationErrors> errorsOptional = dateFromValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
    }

    private Date createNewDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

