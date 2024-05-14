package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
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
class FutureDateToValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @Mock
    private DateTimeUtil dateTimeUtil;

    @InjectMocks
    private FutureDateToValidator futureDateToValidator;

    @Test
    public void errorIfDateToIsFromPast() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateTo()).thenReturn(createNewDate("10.10.2010"));
        when(dateTimeUtil.getTodayDateTime()).thenReturn(createNewDate("10.10.2024"));
        ValidationErrors validationErrors = mock(ValidationErrors.class);
        when(validationErrorFactory.createError("ERROR_CODE_6")).thenReturn(validationErrors);
        Optional<ValidationErrors> errorsOptional = futureDateToValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertSame(errorsOptional.get(), validationErrors);
    }

    @Test
    public void noErrorIfDateToIsFromFuture() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateTo()).thenReturn(createNewDate("10.10.2030"));
        when(dateTimeUtil.getTodayDateTime()).thenReturn(createNewDate("10.10.2024"));
        Optional<ValidationErrors> errorsOptional = futureDateToValidator.execute(premiumRequest);
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
