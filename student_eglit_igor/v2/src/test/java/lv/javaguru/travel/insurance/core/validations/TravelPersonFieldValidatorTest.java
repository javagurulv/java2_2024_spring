package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TravelPersonFieldValidatorTest {

    @InjectMocks
    private TravelPersonFieldValidator validator;

    @Test
    public void shouldNotReturnErrors() {
        var agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("Name", "Surname", LocalDate.of(2000, 1, 1), "MEDICAL_RISK",List.of(new RiskDTO()));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        lenient().when(validation1.validate(agreement, person)).thenReturn(Optional.empty());
        lenient().when(validation1.validateList(agreement, person)).thenReturn(List.of());
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        lenient().when(validation2.validate(agreement, person)).thenReturn(Optional.empty());
        lenient().when(validation2.validateList(agreement, person)).thenReturn(List.of());

        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSinglePersonErrors() {
        var agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("Name", "Surname", LocalDate.of(2000, 1, 1), "MEDICAL_RISK", List.of(new RiskDTO()));
        lenient().when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(agreement, person)).thenReturn(Optional.of(new ValidationErrorDTO("code1", "description1")));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(agreement, person)).thenReturn(Optional.of(new ValidationErrorDTO("code2", "description2")));

        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertEquals(2, errors.size());
        assertEquals("code1", errors.get(0).getErrorCode());
        assertEquals("description1", errors.get(0).getDescription());
        assertEquals("code2", errors.get(1).getErrorCode());
        assertEquals("description2", errors.get(1).getDescription());
    }

    @Test
    public void shouldReturnLIstPersonErrors() {
        var agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("Name", "Surname", LocalDate.of(2000, 1, 1), "MEDICAL_RISK", List.of(new RiskDTO()));
        lenient().when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validateList(agreement, person)).thenReturn(List.of(new ValidationErrorDTO("code1", "description1")));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validateList(agreement, person)).thenReturn(List.of(new ValidationErrorDTO("code2", "description2")));
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(2, errors.size());
    }
}