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
class ValidatePersonalCodeNotNullTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonalCodeNotNullOrBlank validate;

    @ParameterizedTest(name = "{0}")
    @MethodSource("personalCodeValue")
    public void validate_ShouldReturnErrorWhenPersonalCodeIsNullOrBlank(String testName, String personalCode) {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement().build();
        PersonDTO person = PersonDTOBuilder.createPerson()
                .withPersonalCode(personalCode)
                .build();

        when(errorMock.buildError("ERROR_CODE_9"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_9", "Field personalCode is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement, person);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(error -> {
                    assertThat(error.errorCode()).isEqualTo("ERROR_CODE_9");
                    assertThat(error.description()).isEqualTo("Field personalCode is empty!");
                });
    }

    private static Stream<Arguments> personalCodeValue() {
        return Stream.of(
                Arguments.of("personalCode is null", null),
                Arguments.of("personalCode is empty", ""),
                Arguments.of("personalCode is blank", "     ")
        );
    }

}