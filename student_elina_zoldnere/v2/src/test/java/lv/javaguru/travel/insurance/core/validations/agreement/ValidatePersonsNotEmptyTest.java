package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonsNotEmptyTest {

    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidatePersonsNotEmpty validate;

    @Test
    void validateSingle_ShouldReturnErrorWhenPersonsIsEmpty() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withPersons(Collections.emptyList())
                .build();

        when(errorFactoryMock.buildError("ERROR_CODE_18"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_18", "No persons added to agreement! " +
                        "Agreement must contain at least one person!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(error -> {
                    assertThat(error.errorCode()).isEqualTo("ERROR_CODE_18");
                    assertThat(error.description()).isEqualTo("No persons added to agreement! " +
                            "Agreement must contain at least one person!");
                });
    }

}