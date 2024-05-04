package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private AgreementDTO agreementMock;
    @Mock
    private PersonDTO personMock1;
    @Mock
    private PersonDTO personMock2;
    @Mock
    private List<AgreementFieldValidation> agreementFieldValidation;
    @Mock
    private List<PersonFieldValidation> personFieldValidation;
    @InjectMocks
    private TravelAgreementValidatorImpl validator;

    @Test
    void validate_ShouldPassWhenAllValidationsSucceed() {
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(agreementMock.getPersons()).thenReturn(Collections.emptyList());

        List<ValidationErrorDTO> errors = validator.validate(agreementMock);

        assertEquals(Collections.emptyList(), errors);
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonSingleValidationFail() {
        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(agreementMock.getPersons()).thenReturn(List.of(personMock1));
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateSingle(any())).thenReturn(Optional.of(new ValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreementMock);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonListValidationFail() {
        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(agreementMock.getPersons()).thenReturn(List.of(personMock1));
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateList(any())).thenReturn(List.of(new ValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreementMock);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenAgreementSingleValidationFail() {
        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementMock.getPersons()).thenReturn(Collections.emptyList());
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateSingle(any())).thenReturn(Optional.of(new ValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreementMock);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenAgreementListValidationFail() {
        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        when(agreementMock.getPersons()).thenReturn(Collections.emptyList());
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateList(any())).thenReturn(List.of(new ValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreementMock);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenSeveralMixedValidationFail() {
        AgreementFieldValidation agreementValidationMock = mock(AgreementFieldValidation.class);
        PersonFieldValidation personValidationMock1 = mock(PersonFieldValidation.class);
        PersonFieldValidation personValidationMock2 = mock(PersonFieldValidation.class);

        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementMock.getPersons()).thenReturn(List.of(personMock1, personMock2));
        when(personFieldValidation.stream())
                .thenAnswer(invocation -> Stream.of(personValidationMock1, personValidationMock2))
                .thenAnswer(invocation -> Stream.of(personValidationMock1));

        when(agreementValidationMock.validateList(any()))
                .thenReturn(List.of(new ValidationErrorDTO(), new ValidationErrorDTO()));
        when(agreementValidationMock.validateSingle(any())).thenReturn(Optional.of(new ValidationErrorDTO()));
        when(personValidationMock1.validateSingle(any())).thenReturn(Optional.of(new ValidationErrorDTO()));
        when(personValidationMock2.validateSingle(any())).thenReturn(Optional.of(new ValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.validate(agreementMock);

        assertEquals(6, errors.size());
    }

}
