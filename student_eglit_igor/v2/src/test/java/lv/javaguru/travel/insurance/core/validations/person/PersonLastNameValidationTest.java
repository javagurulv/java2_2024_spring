package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonLastNameValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private PersonLastNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        var person = new PersonDTO("John", null, null, null);
        when(errorFactory.buildError("ERROR_CODE_2")).thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(person);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_2", errorOpt.get().getErrorCode());
        assertEquals("Field personLastName is empty!", errorOpt.get().getDescription());
    }
    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        var person = new PersonDTO("John", "", null, null);
        var validationError = new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!");
        when(errorFactory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(person);
        assertTrue(errorOpt.isPresent());
        assertSame(validationError, errorOpt.get());
    }
    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        var person = new PersonDTO("John", "Doe", null, null);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(person);
        assertTrue(errorOpt.isEmpty());
    }

}