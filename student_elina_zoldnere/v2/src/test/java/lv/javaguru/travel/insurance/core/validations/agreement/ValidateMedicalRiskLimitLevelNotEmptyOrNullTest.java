package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidateSetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateMedicalRiskLimitLevelNotEmptyOrNullTest {

    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateMedicalRiskLimitLevelNotEmpty validateRiskLimitLevel;

    @Autowired
    @InjectMocks
    private ValidateSetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("medicalRiskLimitLevelValue")
    public void validateSingle_ShouldReturnErrorWhenMedicalRiskLimitLevelIsEnabledAndFieldIsNotValid(
            String testName, String medicalRiskLimitLevel) {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                medicalRiskLimitLevel,
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);
        when(errorFactoryMock.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8",
                        "Field medicalRiskLimitLevel is empty when medical risk limit level enabled!"));

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreement);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_8", result.get().errorCode());
        assertEquals("Field medicalRiskLimitLevel is empty when medical risk limit level enabled!",
                result.get().description());
    }

    private static Stream<Arguments> medicalRiskLimitLevelValue() {
        return Stream.of(
                Arguments.of("medical risk limit level null", null),
                Arguments.of("medical risk limit level empty", ""),
                Arguments.of("medical risk limit level blank", "     ")
        );
    }

    @Test
    public void validateSingle_ShouldNotReturnErrorWhenMedicalRiskLimitLevelIsDisabled() {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.FALSE);
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                null,
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreement);

        assertTrue(result.isEmpty());
        verifyNoInteractions(errorFactoryMock);
    }

}