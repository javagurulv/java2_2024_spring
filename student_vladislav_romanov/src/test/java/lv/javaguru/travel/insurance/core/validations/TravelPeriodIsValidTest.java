package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ErrorCodeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPeriodIsValidTest {

    @Mock
    private ErrorCodeUtil errorCodeUtil;
    @InjectMocks
    private final TravelPeriodIsValid validation = new TravelPeriodIsValid();

    @Test
    void travelPeriodIsNotValid() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2030, 3, 31));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2030, 3, 21));
        when(errorCodeUtil.getErrorDescription(7)).thenReturn("Travel period contain incorrect data!");
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getErrorCode(), 7);
        assertEquals(errors.get().getDescription(), "Travel period contain incorrect data!");
    }

    @Test
    void travelPeriodIsValid() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2030, 3, 21));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2030, 3, 31));
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isEmpty());
    }
}
