package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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

class RequestValidationEmptyRiskPremiumTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationEmptySelectedRisks requestValidationEmptySelectedRisks;
    private TravelCalculatePremiumRequestV1 request;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequestV1();
        when(validationErrorFactory.buildError("ERROR_CODE_8")).thenReturn(new ValidationError("ERROR_CODE_8","Field selected_risks is empty!"));

    }
    @Test
    void shouldReturnErrorWhenInsuranceRisksIsNull(){
        request.setSelectedRisks(null);
        Optional<ValidationError> error = requestValidationEmptySelectedRisks.validateSingle(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_8", error.get().getErrorCode());
        assertEquals("Field selected_risks is empty!", error.get().getDescription());
    }
    @Test
    void shouldReturnErrorWhenInsuranceRisksIsEmpty(){
        request.setSelectedRisks(List.of());
        Optional<ValidationError> error = requestValidationEmptySelectedRisks.validateSingle(request);
        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_8", error.get().getErrorCode());
        assertEquals("Field selected_risks is empty!", error.get().getDescription());
    }
}