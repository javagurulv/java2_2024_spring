package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RequestValidationCountryIsNotNullThenSelected_risksIsTravel_MedicalTest {
    @InjectMocks
    private RequestValidationCountryIsNotNullThenSelected_risksIsTravel_Medical validator;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnEmptyWhenCountryIsNotNullAndRiskIsTravelMedical() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setCountry("Latvia");
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));

        Optional<ValidationError> result = validator.validateReq(request);

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenCountryIsNullAndRiskIsTravelMedical() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));

        ValidationError error = new ValidationError("ERROR_CODE_10","Field Country is empty!");
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(error);

        Optional<ValidationError> result = validator.validateReq(request);

        assertTrue(result.isPresent());
        assertEquals(error, result.get());
    }

    @Test
    public void shouldReturnEmptyWhenRiskIsNotTravelMedical() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setCountry("Latvia");
        request.setSelectedRisks(List.of("TRAVEL_CANCELLATION"));

        Optional<ValidationError> result = validator.validateReq(request);

        assertTrue(result.isEmpty());
    }
}