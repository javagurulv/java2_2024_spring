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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAgreementDateFromNotNullTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateFromNotNull validate;

    @Test
    void validate_ShouldReturnErrorWhenAgreementDateFromIsNull() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(null)
                .build();

        when(errorMock.buildError("ERROR_CODE_3"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_3",
                        "Field agreementDateFrom is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement);

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(error -> {
                    assertThat(error.errorCode()).isEqualTo("ERROR_CODE_3");
                    assertThat(error.description()).isEqualTo("Field agreementDateFrom is empty!");
                });
    }

}