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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonFirstNameValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private PersonFirstNameValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
       var person = new PersonDTO(null, "Doe", "12345",null, null, BigDecimal.ONE, List.of());
        when(errorFactory.buildError("ERROR_CODE_1")).thenReturn(new ValidationErrorDTO("ERROR_CODE_1", "Field personFirstName is empty!"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_1", errorOpt.get().getErrorCode());
        assertEquals("Field personFirstName is empty!", errorOpt.get().getDescription());
    }
    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("", "Doe", "12345",null, null, BigDecimal.ONE, List.of());
        var validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_1")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(validationError, errorOpt.get());
    }
    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("John", "Doe", "12345",null, null, BigDecimal.ONE, List.of());
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isEmpty());
    }

}