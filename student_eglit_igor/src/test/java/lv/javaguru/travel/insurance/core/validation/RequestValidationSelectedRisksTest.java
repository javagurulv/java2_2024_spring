package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.ErrorCodeService;
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

class RequestValidationSelectedRisksTest {
    @Mock
    private ErrorCodeService errorCodeService;
    @InjectMocks
    private RequestValidationSelectedRisks requestValidationSelectedRisks;
    private TravelCalculatePremiumRequest request;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequest();
        when(errorCodeService.getErrorCodeDescription("ERROR_CODE_8")).thenReturn("Field selected_risks is empty!");

    }
    @Test
    void shouldReturnErrorWhenInsuranceRisksIsNull(){
        request.setInsuranceRisks(null);
        Optional<ValidationError> error = requestValidationSelectedRisks.executeValidation(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_8", error.get().getErrorCode());
        assertEquals("Field selected_risks is empty!", error.get().getDescription());
    }
    @Test
    void shouldReturnErrorWhenInsuranceRisksIsEmpty(){
        request.setInsuranceRisks(List.of());
        Optional<ValidationError> error = requestValidationSelectedRisks.executeValidation(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_8", error.get().getErrorCode());
        assertEquals("Field selected_risks is empty!", error.get().getDescription());
    }
}