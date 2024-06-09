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
class ValidatePersonLastNameNotNullOrBlankTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonLastNameNotNullOrBlank validate;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("lastNameValue")
    public void validate_ShouldReturnErrorWhenPersonLastNameIsNotValid(String testName, String lastName) {
        AgreementDTO agreement = helper.newAgreementDTO();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonFirstName("Jānis")
                .withPersonLastName(null)
                .withPersonalCode("123456-12345")
                .withPersonBirthdate(helper.newDate("1990.01.01"))
                .withMedicalRiskLimitLevel("LEVEL_10000")
                .build();

        when(errorMock.buildError("ERROR_CODE_2"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_2", result.get().errorCode());
        assertEquals("Field personLastName is empty!", result.get().description());
    }

    private static Stream<Arguments> lastNameValue() {
        return Stream.of(
                Arguments.of("personLastName null", null),
                Arguments.of("personLastName empty", ""),
                Arguments.of("personLastName blank", "     ")
        );
    }

}