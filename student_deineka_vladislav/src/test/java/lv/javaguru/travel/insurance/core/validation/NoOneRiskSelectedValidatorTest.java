package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NoOneRiskSelectedValidatorTest {

    private NoOneRiskSelectedValidator noOneRiskSelectedValidator = new NoOneRiskSelectedValidator();

    @Test
    public void errorIfSelectedRiskIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(null);
        Optional<ValidationErrors> errorsOptional = noOneRiskSelectedValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "selectedRisk");
        assertEquals(errorsOptional.get().getMessage(), "Field cannot be empty");
    }

    @Test
    public void errorIfSelectedRiskIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(List.of());
        Optional<ValidationErrors> errorsOptional = noOneRiskSelectedValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals(errorsOptional.get().getField(), "selectedRisk");
        assertEquals(errorsOptional.get().getMessage(), "Field cannot be empty");
    }

    @Test
    public void noErrorIfSelectedRiskIsPresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        Optional<ValidationErrors> errorsOptional = noOneRiskSelectedValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
    }
}
