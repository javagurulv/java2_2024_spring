package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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
    @InjectMocks
    private SetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("medicalRiskLimitLevelValue")
    public void validateSingle_ShouldReturnErrorWhenMedicalRiskLimitLevelIsEnabledAndFieldIsNotValid(
            String testName, String medicalRiskLimitLevel) {
        ReflectionTestUtils.setField(validateRiskLimitLevel, "medicalRiskLimitLevelEnabled", Boolean.TRUE);
        AgreementDTO agreement = helper.newAgreementDTO();

        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel(medicalRiskLimitLevel)
                .build();

        when(errorFactoryMock.buildError("ERROR_CODE_8"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_8",
                        "Field medicalRiskLimitLevel is empty when medical risk limit level enabled!"));

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreement, person);

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
        AgreementDTO agreement = helper.newAgreementDTO();

        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        Optional<ValidationErrorDTO> result = validateRiskLimitLevel.validateSingle(agreement, person);

        assertTrue(result.isEmpty());
        verifyNoInteractions(errorFactoryMock);
    }

}