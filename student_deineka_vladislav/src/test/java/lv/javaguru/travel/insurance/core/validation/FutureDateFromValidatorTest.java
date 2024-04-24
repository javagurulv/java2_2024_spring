package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
class FutureDateFromValidatorTest {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private FutureDateFromValidator futureDateFromValidator;

    @Test
    public void errorIfDateFromIsFromPast() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(createNewDate("10.10.2010"));
        when(dateTimeService.getTodayDateTime()).thenReturn(createNewDate("10.10.2024"));
        Optional<ValidationErrors> errorsOptional = futureDateFromValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "dateFrom");
        assertEquals(errorsOptional.get().getMessage(), "Cannot be from the past");
    }

    @Test
    public void noErrorIfDateFromIsFromFuture() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(createNewDate("10.10.2030"));
        when(dateTimeService.getTodayDateTime()).thenReturn(createNewDate("10.10.2024"));
        Optional<ValidationErrors> errorsOptional = futureDateFromValidator.execute(premiumRequest);
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

