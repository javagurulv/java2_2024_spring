package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestValidationMedicalRiskLimitLevelEnabledAndRequestFieldMedicalRiskLimitLevelNotNullTest {
    @Mock
    private ValidationErrorFactory validationErrorFactory;
    @InjectMocks
    private RequestValidationMedicalRiskLimitLevelEnabledAndRequestFieldMedicalRiskLimitLevelNotNull requestMedicalRiskLimit;
    private TravelCalculatePremiumRequestV1 request;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        request = new TravelCalculatePremiumRequestV1();
    }
    @Test
    void shouldReturnErrorWhenMedicalRiskLimitLevelIsNullAndMedicalRiskLimitLevelEnabled(){
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        request.setMedicalRiskLimitLevel(null);
        when(validationErrorFactory.buildError("ERROR_CODE_14")).thenReturn(new ValidationError("ERROR_CODE_14","Field Medical_Risk_Limit_Level should not be empty then Limit is Enabled!"));
        ReflectionTestUtils.setField(requestMedicalRiskLimit, "medicalRiskLimitLevelEnabled", true);
        Optional<ValidationError> result = requestMedicalRiskLimit.validateSingle(request);
        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_14", result.get().getErrorCode());
        assertEquals("Field Medical_Risk_Limit_Level should not be empty then Limit is Enabled!", result.get().getDescription());
    }

    @Test
    void shouldReturnEmptyWhenMedicalRiskLimitLevelIsNotNullAndMedicalRiskLimitLevelEnabled(){
        request.setMedicalRiskLimitLevel("LIMIT_10000");
        ReflectionTestUtils.setField(requestMedicalRiskLimit, "medicalRiskLimitLevelEnabled", true);
        Optional<ValidationError> result = requestMedicalRiskLimit.validateSingle(request);
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldReturnEmptyWhenMedicalRiskLimitLevelIsNullAndMedicalRiskLimitLevelDisabled(){
        request.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        request.setMedicalRiskLimitLevel(null);
        ReflectionTestUtils.setField(requestMedicalRiskLimit, "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationError> result = requestMedicalRiskLimit.validateSingle(request);
        assertTrue(result.isEmpty());
    }
}