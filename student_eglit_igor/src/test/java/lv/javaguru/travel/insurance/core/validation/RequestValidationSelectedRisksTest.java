package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestValidationSelectedRisksTest {
    private RequestValidationSelectedRisks requestValidationSelectedRisks;
    @BeforeEach
    void setup(){
        requestValidationSelectedRisks = new RequestValidationSelectedRisks();

    }
    @Test
    void shouldReturnErrorWhenInsuranceRisksIsNull(){
        var request = new TravelCalculatePremiumRequest();
        request.setInsuranceRisks(null);
        Optional<ValidationError> error = requestValidationSelectedRisks.executeValidation(request);
        assertTrue(error.isPresent());
        assertEquals("selected_risks", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }
    @Test
    void shouldReturnErrorWhenInsuranceRisksIsEmpty(){
        var request = new TravelCalculatePremiumRequest();
        request.setInsuranceRisks(List.of());
        Optional<ValidationError> error = requestValidationSelectedRisks.executeValidation(request);
        assertTrue(error.isPresent());
        assertEquals("selected_risks", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }
}