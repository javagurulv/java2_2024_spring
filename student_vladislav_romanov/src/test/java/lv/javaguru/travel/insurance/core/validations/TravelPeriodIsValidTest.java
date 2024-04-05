package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelPeriodIsValidTest {

    private final TravelPeriodIsValid validation = new TravelPeriodIsValid();

    @Test
    void travelPeriodIsNotValid() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2030, 3, 31));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2030, 3, 21));
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getField(), "Travel Period");
        assertEquals(errors.get().getMessage(), "contain incorrect data!");
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
