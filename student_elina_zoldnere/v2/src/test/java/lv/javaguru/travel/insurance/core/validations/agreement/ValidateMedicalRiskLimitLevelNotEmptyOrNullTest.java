package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
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
    private AgreementDTO agreementMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateMedicalRiskLimitLevelNotEmpty validateRiskLimitLevel;

    @Autowired
    @InjectMocks
    private ValidateSetUpAgreementValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpAgreementMockWithValues(agreementMock);
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenMedicalRiskLimitLevelIsEnabledAndFieldIsNull() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        when(agreementMock.getMedicalRiskLimitLevel()).thenReturn(null);
        when(errorFactoryMock.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8",
                        "Field medicalRiskLimitLevel is empty when medical risk limit level enabled!"));

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_8", result.get().getErrorCode());
        assertEquals("Field medicalRiskLimitLevel is empty when medical risk limit level enabled!",
                result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenMedicalRiskLimitLevelIsEnabledAndFieldIsBlank() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        when(agreementMock.getMedicalRiskLimitLevel()).thenReturn("     ");
        when(errorFactoryMock.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8",
                        "Field medicalRiskLimitLevel is empty when medical risk limit level enabled!"));

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_8", result.get().getErrorCode());
        assertEquals("Field medicalRiskLimitLevel is empty when medical risk limit level enabled!",
                result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenRiskTypeTravelMedicalIsNotSelected() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        when(agreementMock.getSelectedRisks())
                .thenReturn(Arrays.asList("TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"));

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreementMock);

        assertTrue(result.isEmpty());
        verifyNoInteractions(errorFactoryMock);
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenMedicalRiskLimitLevelIsDisabled() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.FALSE);

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreementMock);

        assertTrue(result.isEmpty());
        verifyNoInteractions(errorFactoryMock);
    }

}