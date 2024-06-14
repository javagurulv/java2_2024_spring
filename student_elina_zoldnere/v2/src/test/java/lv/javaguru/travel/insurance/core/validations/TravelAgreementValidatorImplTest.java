package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTOBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelAgreementValidatorImplTest {

    @Mock
    private TravelAgreementAllFieldValidator agreementAllFieldValidator;
    @Mock
    private TravelPersonAllFieldValidator personAllFieldValidator;

    @InjectMocks
    private TravelAgreementValidatorImpl validator;

    @Test
    void validate_ShouldSumUpErrorsCorrectly() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement().build();
        ValidationErrorDTO validationError1 = ValidationErrorDTOBuilder.createValidationError().build();
        ValidationErrorDTO validationError2 = ValidationErrorDTOBuilder.createValidationError().build();
        ValidationErrorDTO validationError3 = ValidationErrorDTOBuilder.createValidationError().build();

        when(agreementAllFieldValidator.collectAgreementErrors(agreement))
                .thenReturn(List.of(validationError1, validationError2));
        when(personAllFieldValidator.collectPersonErrors(agreement)).thenReturn(List.of(validationError3));

        List<ValidationErrorDTO> result = validator.validate(agreement);

        assertThat(result).hasSize(3);
    }

}
