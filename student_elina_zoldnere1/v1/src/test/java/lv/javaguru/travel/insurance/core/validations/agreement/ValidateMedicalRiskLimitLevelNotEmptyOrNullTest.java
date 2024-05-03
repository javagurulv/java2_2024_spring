package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.validations.ValidateSetUpRequestHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateMedicalRiskLimitLevelNotEmptyOrNullTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateMedicalRiskLimitLevelNotEmpty validateRiskLimitLevel;

    @Autowired
    @InjectMocks
    private ValidateSetUpRequestHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenMedicalRiskLimitLevelIsEnabledAndFieldIsNull() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        when(requestMock.getMedicalRiskLimitLevel()).thenReturn(null);
        when(errorFactoryMock.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationError("ERROR_CODE_8",
                        "Field medicalRiskLimitLevel is empty when medical risk limit level enabled!"));

        Optional<ValidationError> result = validateRiskLimitLevel.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_8", result.get().getErrorCode());
        assertEquals("Field medicalRiskLimitLevel is empty when medical risk limit level enabled!",
                result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenMedicalRiskLimitLevelIsEnabledAndFieldIsBlank() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        when(requestMock.getMedicalRiskLimitLevel()).thenReturn("     ");
        when(errorFactoryMock.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationError("ERROR_CODE_8",
                        "Field medicalRiskLimitLevel is empty when medical risk limit level enabled!"));

        Optional<ValidationError> result = validateRiskLimitLevel.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_8", result.get().getErrorCode());
        assertEquals("Field medicalRiskLimitLevel is empty when medical risk limit level enabled!",
                result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenRiskTypeTravelMedicalIsNotSelected() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        when(requestMock.getSelectedRisks())
                .thenReturn(Arrays.asList("TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));

        Optional<ValidationError> result = validateRiskLimitLevel.validateSingle(requestMock);

        assertTrue(result.isEmpty());
        verifyNoInteractions(errorFactoryMock);
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenMedicalRiskLimitLevelIsDisabled() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.FALSE);

        Optional<ValidationError> result = validateRiskLimitLevel.validateSingle(requestMock);

        assertTrue(result.isEmpty());
        verifyNoInteractions(errorFactoryMock);
    }

}