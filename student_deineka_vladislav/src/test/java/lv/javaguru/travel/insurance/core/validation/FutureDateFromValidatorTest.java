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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FutureDateFromValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private FutureDateFromValidator futureDateFromValidator;

    @Test
    public void errorIfDateFromIsFromPast() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(createNewDate("10.10.2010"));
        when(dateTimeService.getTodayDateTime()).thenReturn(createNewDate("10.10.2024"));
        ValidationErrors validationErrors = mock(ValidationErrors.class);
        when(validationErrorFactory.createError("ERROR_CODE_5")).thenReturn(validationErrors);
        Optional<ValidationErrors> errorsOptional = futureDateFromValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertSame(errorsOptional.get(), validationErrors);
    }

    @Test
    public void noErrorIfDateFromIsFromFuture() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateFrom()).thenReturn(createNewDate("10.10.2030"));
        when(dateTimeService.getTodayDateTime()).thenReturn(createNewDate("10.10.2024"));
        Optional<ValidationErrors> errorsOptional = futureDateFromValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
        verifyNoInteractions(validationErrorFactory);
    }


    private Date createNewDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

