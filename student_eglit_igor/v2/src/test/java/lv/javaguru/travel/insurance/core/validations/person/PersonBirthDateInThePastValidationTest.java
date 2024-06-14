package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonBirthDateInThePastValidationTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonBirthDateInThePastValidation validation;

    @Test
    public void shouldReturnErrorWhenPersonBirthDateInTheFuture() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("John", "Doe", "12345",LocalDate.now().plusYears(20), "MEDICAL_RISK", BigDecimal.ONE, Collections.emptyList());
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.now());
        when(errorFactory.buildError("ERROR_CODE_13")).thenReturn(new ValidationErrorDTO("ERROR_CODE_13", "Field personBirthDate is in the future!"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_13", errorOpt.get().getErrorCode());
        assertEquals("Field personBirthDate is in the future!", errorOpt.get().getDescription());
    }
    @Test
    public void shouldNotReturnErrorWhenPersonBirthDateDateInThePast() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        var person = new PersonDTO("John", "Doe", "12345",LocalDate.now().minusYears(20), "MEDICAL_RISK", BigDecimal.ONE, Collections.emptyList());
        when(dateTimeUtil.getCurrentDateTime()).thenReturn(LocalDate.now());
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement,person);
        assertTrue(errorOpt.isEmpty());
    }
}