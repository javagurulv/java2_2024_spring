package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalRiskLimitLevelValidationTest {
    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private ClassifierValueRepository classifierValueRepository;
    @InjectMocks
    private MedicalRiskLimitLevelValidation validation;

    @Test
    public void shouldNotReturnErrorWhenMedicalRiskLimitLevelExistInDb() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = new PersonDTO("John", "Doe", LocalDate.of(2000, 1, 1), "LEVEL_10000", Collections.emptyList());
        lenient().when(agreement.getPersons()).thenReturn(List.of(person));
        ClassifierValue classifierValue = mock(ClassifierValue.class);
        lenient().when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "LEVEL_10000"))
                .thenReturn(Optional.of(classifierValue));
        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreement, person);
        assertTrue(validationErrorOpt.isEmpty());
        verifyNoInteractions(errorFactory);
    }

    @Test
    public void shouldReturnErrorWhenMedicalRiskLimitLevelNotExistInDb() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        PersonDTO person = new PersonDTO("John", "Doe", LocalDate.of(2000, 1, 1), "NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL", Collections.emptyList());
        lenient().when(agreement.getPersons()).thenReturn(List.of(person));
        lenient().when(classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", "NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL"))
                .thenReturn(Optional.empty());

        lenient().when(errorFactory.buildError(eq("ERROR_CODE_15"), anyList())).thenReturn(error);
        Optional<ValidationErrorDTO> validationErrorOpt = validation.validate(agreement, person);
        assertTrue(validationErrorOpt.isPresent());
        assertSame(error, validationErrorOpt.get());
    }
}