package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SelectedRiskValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private ClassifierValueRepository classifierValueRepository;
    @InjectMocks
    private SelectedRiskValidation validation;

    @Test
    public void shouldNotValidateWhenSelectedRisksIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getSelectedRisks()).thenReturn(null);
        assertTrue(validation.validateList(agreement).isEmpty());
        verifyNoInteractions(classifierValueRepository, errorFactory);
    }

    @Test
    public void shouldValidateWithoutErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getSelectedRisks()).thenReturn(List.of("RISK_IC_1", "RISK_IC_2"));
        ClassifierValue classifierValue = mock(ClassifierValue.class);
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK TYPE", "RISK_IC_1"))
                .thenReturn(Optional.of(classifierValue));
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK TYPE", "RISK_IC_2"))
                .thenReturn(Optional.of(classifierValue));
        assertTrue(validation.validateList(agreement).isEmpty());
    }

    @Test
    public void shouldValidateWithErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getSelectedRisks()).thenReturn(List.of("RISK_IC_1", "RISK_IC_2"));
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK TYPE", "RISK_IC_1"))
                .thenReturn(Optional.empty());
        when(classifierValueRepository.findByClassifierTitleAndIc("RISK TYPE", "RISK_IC_2"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError(eq("ERROR_CODE_9"), anyList())).thenReturn(error);

        assertEquals(validation.validateList(agreement).size(), 2);
    }

}