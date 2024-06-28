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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyCountryValidationTest {

    @Mock
    private ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    private EmptyCountryValidator countryValidation;

    @Test
    public void noErrorIfSelectedRiskIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(null);
        Optional<ValidationErrors> errorsOptional = countryValidation.validate(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
    }

    @Test
    public void noErrorIfSelectedRiskWithoutTravelMedical() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_EVACUATION"));
        Optional<ValidationErrors> errorsOptional = countryValidation.validate(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
    }

    @Test
    public void noErrorIfSelectedRiskWithTravelMedicalAndWithCountry() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(premiumRequest.getCountry()).thenReturn("SPAIN");
        Optional<ValidationErrors> errorsOptional = countryValidation.validate(premiumRequest);
        assertTrue(errorsOptional.isEmpty());
    }

    @Test
    public void errorIfSelectedRiskWithTravelMedicalAndCountryIsNull() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(premiumRequest.getCountry()).thenReturn(null);
        when(validationErrorFactory.createError("ERROR_CODE_10"))
                .thenReturn(new ValidationErrors("ERROR_CODE_10", "Field Country cannot be empty if TRAVEL_MEDICAL is selected"));
        Optional<ValidationErrors> errorsOptional = countryValidation.validate(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals("ERROR_CODE_10", errorsOptional.get().getErrorCode());
        assertEquals("Field Country cannot be empty if TRAVEL_MEDICAL is selected", errorsOptional.get().getDescription());
    }

    @Test
    public void errorIfSelectedRiskWithTravelMedicalAndCountryIsEmpty() {
        TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(premiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(premiumRequest.getCountry()).thenReturn("");
        when(validationErrorFactory.createError("ERROR_CODE_10"))
                .thenReturn(new ValidationErrors("ERROR_CODE_10", "Field Country cannot be empty if TRAVEL_MEDICAL is selected"));
        Optional<ValidationErrors> errorsOptional = countryValidation.validate(premiumRequest);
        assertTrue(errorsOptional.isPresent());
        assertEquals("ERROR_CODE_10", errorsOptional.get().getErrorCode());
        assertEquals("Field Country cannot be empty if TRAVEL_MEDICAL is selected", errorsOptional.get().getDescription());
    }


}
