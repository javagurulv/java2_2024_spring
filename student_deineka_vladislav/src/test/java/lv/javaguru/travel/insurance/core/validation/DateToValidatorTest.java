package lv.javaguru.travel.insurance.core.validation;

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
class DateToValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private DateToValidator dateToValidator;

    @Test
    public void errorIfDateToIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateTo()).thenReturn(null);
        ValidationErrors validationErrors = mock(ValidationErrors.class);
        when(validationErrorFactory.createError("ERROR_CODE_4")).thenReturn(validationErrors);
        Optional<ValidationErrors> errorsOptional = dateToValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertSame(errorsOptional.get(), validationErrors);
    }


    @Test
    public void noErrorIfDateToIsPresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getAgreementDateTo()).thenReturn(createNewDate("10.10.2030"));
        Optional<ValidationErrors> errorsOptional = dateToValidator.execute(premiumRequest);
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

