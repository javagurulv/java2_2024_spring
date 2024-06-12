package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
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
class TravelPersonAllFieldValidatorTest {

    @Mock
    private List<PersonFieldValidation> personFieldValidation;

    @InjectMocks
    private TravelPersonAllFieldValidator validator;

    @Test
    void collectPersonErrors_ShouldReturnErrorWhenPersonSingleValidationFail() {
        PersonDTO person = PersonDTOBuilder.createPerson().build();
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withPerson(person)
                .build();
        ValidationErrorDTO validationError = ValidationErrorDTOBuilder.createValidationError().build();

        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateSingle(any(), any()))
                .thenReturn(Optional.of(validationError));

        List<ValidationErrorDTO> result = validator.collectPersonErrors(agreement);

        assertThat(result).hasSize(1);
    }

    @Test
    void collectPersonErrors_ShouldReturnErrorWhenPersonListValidationFail() {
        PersonDTO person = PersonDTOBuilder.createPerson().build();
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withPerson(person)
                .build();
        ValidationErrorDTO validationError = ValidationErrorDTOBuilder.createValidationError().build();

        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateList(any())).thenReturn(List.of(validationError));

        List<ValidationErrorDTO> result = validator.collectPersonErrors(agreement);

        assertThat(result).hasSize(1);
    }

}
