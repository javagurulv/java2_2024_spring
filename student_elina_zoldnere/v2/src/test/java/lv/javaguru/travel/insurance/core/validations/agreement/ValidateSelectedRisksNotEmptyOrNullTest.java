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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateSelectedRisksNotEmptyOrNullTest {

    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateSelectedRisksNotEmptyOrNull validate;

    @ParameterizedTest(name = "{0}")
    @MethodSource("selectedRisksValues")
    void validateSingle_ShouldReturnErrorWhenSelectedRisksAreNotValid(
            String testName, List<String> selectedRisks) {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withSelectedRisks(selectedRisks)
                .build();

        when(errorFactoryMock.buildError("ERROR_CODE_5"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_5", "Field selectedRisks is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(e -> {
                    assertThat(e.errorCode()).isEqualTo("ERROR_CODE_5");
                    assertThat(e.description()).isEqualTo("Field selectedRisks is empty!");
                });
    }

    private static Stream<Arguments> selectedRisksValues() {
        return Stream.of(
                Arguments.of("Selected risks is null", null),
                Arguments.of("Selected risks is empty", Collections.emptyList())
        );
    }

}