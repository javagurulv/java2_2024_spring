package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonLastNameValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private PersonLastNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("John", null, "12345",null, null, List.of());
        when(errorFactory.buildError("ERROR_CODE_2")).thenReturn(new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_2", errorOpt.get().getErrorCode());
        assertEquals("Field personLastName is empty!", errorOpt.get().getDescription());
    }
    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("John", "", "12345",null, null, List.of());
        var validationError = new ValidationErrorDTO("ERROR_CODE_2", "Field personLastName is empty!");
        when(errorFactory.buildError("ERROR_CODE_2")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(validationError, errorOpt.get());
    }
    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("John", "Doe", "12345",null, null, List.of());
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isEmpty());
    }

}