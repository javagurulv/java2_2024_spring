package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonalCodeNotNullTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonalCodeNotNullOrBlank validate;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("personalCodeValue")
    public void validate_ShouldReturnErrorWhenPersonFirstNameIsNotValid(String testName, String personalCode) {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName("Bērziņš")
                .withPersonalCode(personalCode)
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        when(errorMock.buildError("ERROR_CODE_9"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_9", "Field personalCode is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_9", result.get().errorCode());
        assertEquals("Field personalCode is empty!", result.get().description());
    }

    private static Stream<Arguments> personalCodeValue() {
        return Stream.of(
                Arguments.of("personalCode null", null),
                Arguments.of("personalCode empty", ""),
                Arguments.of("personalCode blank", "     ")
        );
    }

}