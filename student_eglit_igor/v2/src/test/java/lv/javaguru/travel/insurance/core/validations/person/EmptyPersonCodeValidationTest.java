package lv.javaguru.travel.insurance.core.validations.person;

import liquibase.pro.packaged.E;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyPersonCodeValidationTest extends TravelPersonFieldValidationImpl {

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private EmptyPersonCodeValidation validation;

    @Test
    public void shouldReturnNoErrorWhenPersonCodeIsPresent(){
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = new PersonDTO(
                "John",
                "Doe",
                "12345",
                LocalDate.of(2000,1,1),
                "MEDICAL_RISK",
                List.of()
        );
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement, person);
        assertTrue(errorOpt.isEmpty());
    }

    @Test
    public void shouldReturnErrorWhenPersonCodeIsNull(){
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = new PersonDTO(
                "John",
                "Doe",
                null,
                LocalDate.of(2000,1,1),
                "MEDICAL_RISK",
                List.of()
        );
        when(errorFactory.buildError("ERROR_CODE_16"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_16","Field personCode is empty!"));
        Optional<ValidationErrorDTO> errorOpt=validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_16", errorOpt.get().getErrorCode());
        assertEquals("Field personCode is empty!", errorOpt.get().getDescription());
    }
    @Test
    public void shouldReturnErrorWhenPersonCodeIsEmpty(){
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = new PersonDTO(
                "John",
                "Doe",
                "",
                LocalDate.of(2000,1,1),
                "MEDICAL_RISK",
                List.of()
        );
        when(errorFactory.buildError("ERROR_CODE_16"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_16","Field personCode is empty!"));
        Optional<ValidationErrorDTO> errorOpt=validation.validate(agreement, person);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_16", errorOpt.get().getErrorCode());
        assertEquals("Field personCode is empty!", errorOpt.get().getDescription());
    }
}