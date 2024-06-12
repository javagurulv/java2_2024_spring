package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePersonFieldAnnotationsTest {

    @Mock
    private Validator validatorMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidatePersonFieldAnnotations validate;

    @Test
    void validate_ShouldReturnErrorWithOneConstraintViolation() {
        PersonDTO person = PersonDTOBuilder.createPerson().build();

        ConstraintViolation<PersonDTO> violationMock = mock(ConstraintViolation.class);
        Set<ConstraintViolation<PersonDTO>> violations = Set.of(violationMock);

        when(validatorMock.validate(person)).thenReturn(violations);
        when(errorMock.buildError(eq("ERROR_CODE_71"), anyList()))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_71",
                        "DESCRIPTION"));

        List<ValidationErrorDTO> result = validate.validateList(person);

        assertThat(result)
                .hasSize(1)
                .extracting("errorCode", "description")
                .containsExactly(
                        Assertions.tuple("ERROR_CODE_71", "DESCRIPTION"));
    }


    @Test
    void validate_ShouldReturnErrorWithTwoConstraintViolations() {
        PersonDTO person = PersonDTOBuilder.createPerson().build();

        ConstraintViolation<PersonDTO> firstViolationMock = mock(ConstraintViolation.class);
        ConstraintViolation<PersonDTO> secondViolationMock = mock(ConstraintViolation.class);
        Set<ConstraintViolation<PersonDTO>> violations = Set.of(firstViolationMock, secondViolationMock);

        when(validatorMock.validate(person)).thenReturn(violations);
        when(errorMock.buildError(eq("ERROR_CODE_71"), anyList()))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_71",
                        "DESCRIPTION"));

        List<ValidationErrorDTO> result = validate.validateList(person);

        assertThat(result).hasSize(2);
    }

    @Test
    void validate_ShouldNotReturnErrorsWhenNoViolations() {
        PersonDTO person = PersonDTOBuilder.createPerson().build();

        when(validatorMock.validate(person)).thenReturn(Collections.emptySet());

        List<ValidationErrorDTO> result = validate.validateList(person);

        assertThat(result).isEmpty();
    }

}