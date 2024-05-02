package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

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
class TravelCalculatePremiumRequestV1ValidatorImplTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private List<RequestAgreementFieldValidation> agreementFieldValidation;
    @Mock
    private List<RequestPersonFieldValidation> personFieldValidation;
    @InjectMocks
    private TravelCalculatePremiumRequestValidatorImpl validator;

    @Test
    void validate_ShouldPassWhenAllValidationsSucceed() {
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());

        List<ValidationError> errors = validator.validate(requestMock);

        assertEquals(Collections.emptyList(), errors);
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonSingleValidationFail() {
        RequestPersonFieldValidation personValidationMock = mock(RequestPersonFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateSingle(any())).thenReturn(Optional.of(new ValidationError()));

        List<ValidationError> errors = validator.validate(requestMock);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenPersonListValidationFail() {
        RequestPersonFieldValidation personValidationMock = mock(RequestPersonFieldValidation.class);
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateList(any())).thenReturn(List.of(new ValidationError()));

        List<ValidationError> errors = validator.validate(requestMock);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenAgreementSingleValidationFail() {
        RequestAgreementFieldValidation agreementValidationMock = mock(RequestAgreementFieldValidation.class);
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateSingle(any())).thenReturn(Optional.of(new ValidationError()));

        List<ValidationError> errors = validator.validate(requestMock);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenAgreementListValidationFail() {
        RequestAgreementFieldValidation agreementValidationMock = mock(RequestAgreementFieldValidation.class);
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.empty());
        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(agreementValidationMock.validateList(any())).thenReturn(List.of(new ValidationError()));

        List<ValidationError> errors = validator.validate(requestMock);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_ShouldReturnErrorWhenSeveralMixedValidationFail() {
        RequestAgreementFieldValidation agreementValidationMock = mock(RequestAgreementFieldValidation.class);
        RequestPersonFieldValidation personValidationMock1 = mock(RequestPersonFieldValidation.class);
        RequestPersonFieldValidation personValidationMock2 = mock(RequestPersonFieldValidation.class);

        when(agreementFieldValidation.stream()).thenAnswer(invocation -> Stream.of(agreementValidationMock));
        when(personFieldValidation.stream())
                .thenAnswer(invocation -> Stream.of(personValidationMock1, personValidationMock2));

        when(agreementValidationMock.validateList(any()))
                .thenReturn(List.of(new ValidationError(), new ValidationError()));
        when(agreementValidationMock.validateSingle(any())).thenReturn(Optional.of(new ValidationError()));
        when(personValidationMock1.validateSingle(any())).thenReturn(Optional.of(new ValidationError()));
        when(personValidationMock2.validateSingle(any())).thenReturn(Optional.of(new ValidationError()));

        List<ValidationError> errors = validator.validate(requestMock);

        assertEquals(5, errors.size());
    }

}
