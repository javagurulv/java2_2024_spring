package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelPersonFieldValidatorTest {

    @InjectMocks
    private TravelPersonFieldValidator validator;

    @Test
    public void shouldNotReturnErrors() {
        var person = new PersonDTO("Name", "Surname", LocalDate.of(2000, 1, 1), List.of(new RiskDTO()));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.empty());
        when(validation1.validateList(person)).thenReturn(List.of());
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.empty());
        when(validation2.validateList(person)).thenReturn(List.of());

        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);

        List<ValidationErrorDTO> errors = validator.validate(List.of(person));

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSinglePersonErrors() {
        var person = new PersonDTO("Name", "Surname", LocalDate.of(2000, 1, 1), List.of(new RiskDTO()));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.of(new ValidationErrorDTO("code1", "description1")));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.of(new ValidationErrorDTO("code2", "description2")));

        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);

        List<ValidationErrorDTO> errors = validator.validate(List.of(person));

        assertEquals(2, errors.size());
        assertEquals("code1", errors.get(0).getErrorCode());
        assertEquals("description1", errors.get(0).getDescription());
        assertEquals("code2", errors.get(1).getErrorCode());
        assertEquals("description2", errors.get(1).getDescription());
    }

    @Test
    public void shouldReturnLIstPersonErrors() {
        var person = new PersonDTO("Name", "Surname", LocalDate.of(2000, 1, 1), List.of(new RiskDTO()));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validateList(person)).thenReturn(List.of(new ValidationErrorDTO("code1", "description1")));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validateList(person)).thenReturn(List.of(new ValidationErrorDTO("code2", "description2")));
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationErrorDTO> errors = validator.validate(List.of(person));
        assertEquals(2, errors.size());
    }
}