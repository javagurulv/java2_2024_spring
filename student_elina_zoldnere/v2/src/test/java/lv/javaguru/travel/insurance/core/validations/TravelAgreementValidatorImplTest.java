package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelAgreementValidatorImplTest {

    @Mock
    private List<AgreementFieldValidation> agreementFieldValidation;
    @Mock
    private List<PersonFieldValidation> personFieldValidation;
    @InjectMocks
    private TravelAgreementValidatorImpl validator;

    @InjectMocks
    @Autowired
    private ValidateSetUpInstancesHelper helper;

    private AgreementDTO agreement;

    @Test
    void validate_ShouldPassWhenAllValidationsSucceed() {
        agreement = helper.newAgreementDTO();
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(Collections.emptyList(), errors);
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonSingleValidationFail() {
        agreement = helper.newAgreementDTO();
        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateSingle(any()))
                .thenReturn(Optional.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonListValidationFail() {
        agreement = helper.newAgreementDTO();
        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateList(any())).thenReturn(List.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenAgreementSingleValidationFail() {
        agreement = helper.newAgreementDTO();
        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateSingle(any())).thenReturn(Optional.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenAgreementListValidationFail() {
        agreement = helper.newAgreementDTO();
        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateList(any())).thenReturn(List.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenSeveralMixedValidationFail() {
        agreement = helper.newTwoPersonsAgreementDTO();
        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        PersonFieldValidation personValidationMock1 = mock(PersonFieldValidation.class);
        PersonFieldValidation personValidationMock2 = mock(PersonFieldValidation.class);

        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(personFieldValidation.stream())
                .thenAnswer(invocation -> Stream.of(personValidationMock1, personValidationMock2))
                .thenAnswer(invocation -> Stream.of(personValidationMock1));

        when(agreementValidationMock.validateList(any()))
                .thenReturn(List.of(newValidationErrorDTO(), newValidationErrorDTO()));
        when(agreementValidationMock.validateSingle(any())).thenReturn(Optional.of(newValidationErrorDTO()));
        when(personValidationMock1.validateSingle(any())).thenReturn(Optional.of(newValidationErrorDTO()));
        when(personValidationMock2.validateSingle(any())).thenReturn(Optional.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(6, errors.size());
    }

    private ValidationErrorDTO newValidationErrorDTO() {
        return new ValidationErrorDTO("errorCode", "description");
    }

}
