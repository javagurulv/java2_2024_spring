package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
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
class ValidateCountryNotEmptyOrNullTest {

    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateCountryNotEmptyOrNull validate;

    @ParameterizedTest(name = "{0}")
    @MethodSource("countryValue")
    public void validateSingle_ShouldReturnErrorWhenCountryIsEmptyOrNull(String testName, String country) {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withCountry(country)
                .build();

        when(errorFactoryMock.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_6", "Field country is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(error -> {
                    assertThat(error.errorCode()).isEqualTo("ERROR_CODE_6");
                    assertThat(error.description()).isEqualTo("Field country is empty!");
                });
    }

    private static Stream<Arguments> countryValue() {
        return Stream.of(
                Arguments.of("Country is null", null),
                Arguments.of("Country is empty", ""),
                Arguments.of("Country is blank", "     ")
        );
    }

}