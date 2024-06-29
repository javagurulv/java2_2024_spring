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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonFieldAnnotationValidationTest {

    @Mock private ValidationErrorFactory errorFactory;

    @InjectMocks
    private PersonFieldAnnotationValidation validation;

    @Test
    public void shouldNotReturnError() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(person.getPersonFirstName()).thenReturn("Vasja");
        when(person.getPersonLastName()).thenReturn("Pupkin");
        List<ValidationErrorDTO> errors = validation.validateList(agreement, person);
        assertTrue(errors.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsTooLong() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = new PersonDTO();
        person.setPersonFirstName("VasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasjaVasja");
        person.setPersonLastName("Pupkin");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError(eq("ERROR_CODE_24"), anyList())).thenReturn(validationError);
        List<ValidationErrorDTO> errors = validation.validateList(agreement, person);
        assertFalse(errors.isEmpty());
        assertSame(errors.get(0), validationError);
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsTooLong() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = new PersonDTO();
        person.setPersonFirstName("Vasja");
        person.setPersonLastName("PupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkinPupkin");
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError(eq("ERROR_CODE_24"), anyList())).thenReturn(validationError);
        List<ValidationErrorDTO> errors = validation.validateList(agreement, person);
        assertFalse(errors.isEmpty());
        assertSame(errors.get(0), validationError);
    }

}