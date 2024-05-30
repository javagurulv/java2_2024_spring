package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
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
class AgreementDateToInFutureValidationTest {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private AgreementDateToInFutureValidation validation;

    @Test
    public void shouldAgreementDateToInThePast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("01.02.2022"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.04.2024"));
        Optional<ValidationError> errorOpt = validation.validateDateToInFuture(request);
        assertTrue(errorOpt.isPresent());
        assertEquals(errorOpt.get().getField(), "agreementDateTo");
        assertEquals(errorOpt.get().getMessage(), "Must be in the future!");
    }

    @Test
    public void shouldAgreementDateToInTheFuture() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(createDate("04.01.2024"));
        when(dateTimeService.getCurrentDateTime()).thenReturn(createDate("01.01.2023"));
        Optional<ValidationError> errorOpt = validation.validateDateToInFuture(request);
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

