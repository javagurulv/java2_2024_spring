package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonLastNameFormatValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonLastNameFormatValidation validation;

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonLastName()).thenReturn(null);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonLastName()).thenReturn("");
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsRightFormatted() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonLastName()).thenReturn("John Doe-Smith");
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNotRightFormatted() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonLastName()).thenReturn("John Doe-Smith 12");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError(eq("ERROR_CODE_23"), anyList())).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }

}
