package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyMedicalRiskLimitLevelValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private EmptyMedicalRiskLimitLevelValidation validation;
    private AgreementDTO agreement;
    private PersonDTO person;
    @BeforeEach
    void setUp(){
        agreement = new AgreementDTO();
    }

    @Test
    public void shouldReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndNullOrBlank() {
        agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        person = new PersonDTO("John", "Doe", LocalDate.of(2000, 1, 1), null, List.of());
        ValidationErrorDTO expectedError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_14")).thenReturn(expectedError);

        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);

        Optional<ValidationErrorDTO> result = validation.validate(agreement, person);

        assertTrue(result.isPresent());
        assertEquals(expectedError, result.get());

    }
    @Test
    public void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelEnabledAndIsNotBlank() {
       agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
       person = new PersonDTO("John", "Doe", LocalDate.of(2000, 1, 1), "MEDICAL_RISK_LIMIT", List.of());
       ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", true);
       Optional<ValidationErrorDTO> result = validation.validate(agreement, person);
       assertTrue(result.isEmpty());
    }
    @Test
    public void shouldNotReturnValidationErrorWhenMedicalRiskLimitLevelDisabled() {
        agreement.setSelectedRisks(List.of("TRAVEL_MEDICAL"));
        person = new PersonDTO("John", "Doe", LocalDate.of(2000, 1, 1), null, List.of());
        ReflectionTestUtils.setField(validation, "medicalRiskLimitLevelEnabled", false);
        Optional<ValidationErrorDTO> result = validation.validate(agreement, person);
        assertTrue(result.isEmpty());
    }

}