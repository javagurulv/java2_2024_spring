package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPersonAllFieldValidatorTest {

    @Mock
    private List<PersonFieldValidation> personFieldValidation;
    @InjectMocks
    private TravelPersonAllFieldValidator validator;

    @InjectMocks
    @Autowired
    private ValidateSetUpInstancesHelper helper;

    private List<PersonDTO> persons;

    @Test
    void collectPersonErrors_ShouldReturnErrorWhenPersonSingleValidationFail() {
        persons = List.of(helper.newPersonDTO());
        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateSingle(any()))
                .thenReturn(Optional.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.collectPersonErrors(persons);

        assertEquals(1, errors.size());
    }

    @Test
    void collectPersonErrors_ShouldReturnErrorWhenPersonListValidationFail() {
        persons = List.of(helper.newPersonDTO());
        PersonFieldValidation personValidationMock = mock(PersonFieldValidation.class);
        when(personFieldValidation.stream()).thenAnswer(invocation -> Stream.of(personValidationMock));
        when(personValidationMock.validateList(any())).thenReturn(List.of(newValidationErrorDTO()));

        List<ValidationErrorDTO> errors = validator.collectPersonErrors(persons);

        assertEquals(1, errors.size());
    }

    private ValidationErrorDTO newValidationErrorDTO() {
        return new ValidationErrorDTO("errorCode", "description");
    }

}
