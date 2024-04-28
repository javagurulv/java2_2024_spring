package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RequestValidationCountryTest {
    @InjectMocks
    private RequestValidationCountry validator;

    @Mock
    private ValidationErrorFactory errorFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnEmptyWhenCountryIsNotNullOrEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setCountry("Latvia");
        request.setSelectedRisks(List.of("TRAVEL_EVACUATION"));

        Optional<ValidationError> result = validator.validateSingle(request);

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenCountryIsEmpty() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("TRAVEL_EVACUATION"));
        request.setCountry("");

        ValidationError error = new ValidationError("ERROR_CODE_10","Field Country is empty!");
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(error);

        Optional<ValidationError> result = validator.validateSingle(request);

        assertTrue(result.isPresent());
        assertEquals(error, result.get());
    }

    @Test
    public void shouldReturnErrorThenCountryIsNull() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setCountry(null);
        request.setSelectedRisks(List.of("TRAVEL_CANCELLATION"));

        ValidationError error = new ValidationError("ERROR_CODE_10","Field Country is empty!");
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(error);

        Optional<ValidationError> result = validator.validateSingle(request);

        assertTrue(result.isPresent());
        assertEquals(error, result.get());
    }
}