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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BirthDateInThePastValidatorTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private BirthDateFromThePastValidator birthDateInThePastValidation;

    @Test
    public void errorIfBirthDateIsInTheFuture() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonBirthDate()).thenReturn(makeDate("10.10.2040"));
        when(dateTimeUtil.getTodayDateTime()).thenReturn(makeDate("10.10.2023"));
        ValidationErrors validationErrors = mock(ValidationErrors.class);
        when(validationErrorFactory.createError("ERROR_CODE_12")).thenReturn(validationErrors);
        Optional<ValidationErrors> errorsOptional = birthDateInThePastValidation.validate(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertSame(errorsOptional.get(), validationErrors);
    }

    @Test
    public void noErrorIfBirthDateIsInThePast() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getPersonBirthDate()).thenReturn(makeDate("10.10.2020"));
        when(dateTimeUtil.getTodayDateTime()).thenReturn(makeDate("10.10.2023"));
        Optional<ValidationErrors> errorsOptional = birthDateInThePastValidation.validate(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
        verifyNoInteractions(validationErrorFactory);
    }

    private Date makeDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
