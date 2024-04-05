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

public class DateToIsNotInPastTest {

    private final DateToIsNotInPast validation = new DateToIsNotInPast();

    @Test
    void dateToIsInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2019, 3, 31));
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isPresent());
        assertEquals(errors.get().getField(), "agreementDateTo");
        assertEquals(errors.get().getMessage(), "date cannot be in past!");
    }
    @Test
    void dateToIsNotInPast() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2030, 3, 31));
        Optional<ValidationError> errors = validation.execute(request);
        assertTrue(errors.isEmpty());
    }

}
