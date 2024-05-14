package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoOneRiskSelectedValidatorTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private NoOneRiskSelectedValidator noOneRiskSelectedValidator;

    @Test
    public void errorIfSelectedRiskIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(null);
        ValidationErrors validationErrors = mock(ValidationErrors.class);
        when(validationErrorFactory.createError("ERROR_CODE_8")).thenReturn(validationErrors);
        Optional<ValidationErrors> errorsOptional = noOneRiskSelectedValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertSame(errorsOptional.get(), validationErrors);
    }

    @Test
    public void errorIfSelectedRiskIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(List.of());
        ValidationErrors validationErrors = mock(ValidationErrors.class);
        when(validationErrorFactory.createError("ERROR_CODE_8")).thenReturn(validationErrors);
        Optional<ValidationErrors> errorsOptional = noOneRiskSelectedValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertSame(errorsOptional.get(), validationErrors);
    }

    @Test
    public void noErrorIfSelectedRiskIsPresent() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        Optional<ValidationErrors> errorsOptional = noOneRiskSelectedValidator.execute(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
        verifyNoInteractions(validationErrorFactory);
    }
}
