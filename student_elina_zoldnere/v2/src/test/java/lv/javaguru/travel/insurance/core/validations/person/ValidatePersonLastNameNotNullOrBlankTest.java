package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonLastNameNotNullOrBlankTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonLastNameNotNullOrBlank validate;

    @ParameterizedTest(name = "{0}")
    @MethodSource("lastNameValue")
    public void validate_ShouldReturnErrorWhenPersonLastNameIsNullOrBlank(String testName, String lastName) {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement().build();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonLastName(lastName)
                .build();

        when(errorMock.buildError("ERROR_CODE_2"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(error -> {
                    assertThat(error.errorCode()).isEqualTo("ERROR_CODE_2");
                    assertThat(error.description()).isEqualTo("Field personLastName is empty!");
                });
    }

    private static Stream<Arguments> lastNameValue() {
        return Stream.of(
                Arguments.of("personLastName is null", null),
                Arguments.of("personLastName is empty", ""),
                Arguments.of("personLastName is blank", "     ")
        );
    }

}