package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyPersonBirthDateValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private EmptyPersonBirthDateValidation validation;

    @Test
    public void shouldReturnNoErrorWhenPersonBirthDateIsPresent() {
        PersonDTO person = new PersonDTO("John", "Doe", LocalDate.now().minusYears(20), Collections.emptyList());
        Optional<ValidationErrorDTO> errorOpt = validation.validate(person);
        assertTrue(errorOpt.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenPersonBirthDateIsNull() {
        PersonDTO person = new PersonDTO("John", "Doe", null, Collections.emptyList());

        when(errorFactory.buildError("ERROR_CODE_12"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_12", "Field personBirthDate is in the future!"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(person);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_12", errorOpt.get().getErrorCode());
        assertEquals("Field personBirthDate is in the future!", errorOpt.get().getDescription());
    }
}