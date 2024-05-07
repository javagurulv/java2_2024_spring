package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
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
public class TravelAgreementValidatorImplTest {

    @InjectMocks
    private TravelAgreementValidatorImpl validator;

    @Test
    public void shouldNotReturnErrors() {
        var person = new PersonDTO("name", "surname", LocalDate.of(2010, 01, 01), List.of());
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.empty());
        when(validation1.validateList(agreement)).thenReturn(List.of());
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.empty());
        when(validation2.validateList(agreement)).thenReturn(List.of());

        TravelPersonFieldValidation validation3 = mock(TravelPersonFieldValidation.class);
        when(validation3.validate(person)).thenReturn(Optional.empty());
        when(validation3.validateList(person)).thenReturn(List.of());
        TravelPersonFieldValidation validation4 = mock(TravelPersonFieldValidation.class);
        when(validation4.validate(person)).thenReturn(Optional.empty());
        when(validation4.validateList(person)).thenReturn(List.of());

        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personValidations = List.of(validation3, validation4);
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);

        List<ValidationErrorDTO> errors = validator.validate(agreement);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSingleAgreementErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO()));
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.of(new ValidationErrorDTO()));
        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personValidations = List.of();
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(2, errors.size());
    }
    @Test
    public void shouldReturnSinglePersonErrors() {
        var person = new PersonDTO("name", "surname", LocalDate.of(2010, 01, 01), List.of());
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.of(new ValidationErrorDTO()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.of(new ValidationErrorDTO()));
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        List<TravelAgreementFieldValidation> agreementValidations = List.of();
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(2,errors.size());
    }

    @Test
    public void shouldReturnListAgreementErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        TravelAgreementFieldValidation validation1 = mock(TravelAgreementFieldValidation.class);
        when(validation1.validate(agreement)).thenReturn(Optional.empty());
        when(validation1.validateList(agreement)).thenReturn(List.of(new ValidationErrorDTO()));
        TravelAgreementFieldValidation validation2 = mock(TravelAgreementFieldValidation.class);
        when(validation2.validate(agreement)).thenReturn(Optional.empty());
        when(validation2.validateList(agreement)).thenReturn(List.of(new ValidationErrorDTO()));
        List<TravelAgreementFieldValidation> agreementValidations = List.of(validation1, validation2);
        List<TravelPersonFieldValidation> personValidations = List.of();
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(2,errors.size());
    }

    @Test
    public void shouldReturnListPersonErrors() {
        var person = new PersonDTO("name", "surname", LocalDate.of(2010, 01, 01), List.of());
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getPersons()).thenReturn(List.of(person));
        TravelPersonFieldValidation validation1 = mock(TravelPersonFieldValidation.class);
        when(validation1.validate(person)).thenReturn(Optional.empty());
        when(validation1.validateList(person)).thenReturn(List.of(new ValidationErrorDTO()));
        TravelPersonFieldValidation validation2 = mock(TravelPersonFieldValidation.class);
        when(validation2.validate(person)).thenReturn(Optional.empty());
        when(validation2.validateList(person)).thenReturn(List.of(new ValidationErrorDTO()));
        List<TravelAgreementFieldValidation> agreementValidations = List.of();
        List<TravelPersonFieldValidation> personValidations = List.of(validation1, validation2);
        ReflectionTestUtils.setField(validator, "agreementFieldValidations", agreementValidations);
        ReflectionTestUtils.setField(validator, "personFieldValidations", personValidations);
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(2, errors.size());
    }

}