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
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelAgreementAllFieldValidatorTest {

    @Mock
    private List<AgreementFieldValidation> agreementFieldValidation;

    @InjectMocks
    private TravelAgreementAllFieldValidator validator;

    @Test
    void collectAgreementErrors_ShouldReturnErrorWhenAgreementSingleValidationFail() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement().build();
        ValidationErrorDTO validationError = ValidationErrorDTOBuilder.createValidationError().build();

        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateSingle(any())).thenReturn(Optional.of(validationError));

        List<ValidationErrorDTO> result = validator.collectAgreementErrors(agreement);

        assertThat(result).hasSize(1);
    }

    @Test
    void collectAgreementErrors_ShouldReturnErrorWhenAgreementListValidationFailWithOneError() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement().build();
        ValidationErrorDTO validationError = ValidationErrorDTOBuilder.createValidationError().build();

        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateList(any())).thenReturn(List.of(validationError));

        List<ValidationErrorDTO> result = validator.collectAgreementErrors(agreement);

        assertThat(result).hasSize(1);
    }

    @Test
    void collectAgreementErrors_ShouldReturnErrorWhenAgreementListValidationFailWithTwoErrors() {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement().build();
        ValidationErrorDTO validationError1 = ValidationErrorDTOBuilder.createValidationError().build();
        ValidationErrorDTO validationError2 = ValidationErrorDTOBuilder.createValidationError().build();

        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateList(any())).thenReturn(List.of(validationError1, validationError2));

        List<ValidationErrorDTO> result = validator.collectAgreementErrors(agreement);

        assertThat(result).hasSize(2);
    }

}
