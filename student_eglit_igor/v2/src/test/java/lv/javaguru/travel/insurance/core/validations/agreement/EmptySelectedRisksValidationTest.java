package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptySelectedRisksValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;
    @InjectMocks
    private EmptySelectedRisksValidation emptySelectedRisksValidation;

    @Test
    public void shouldReturnErrorWhenSelectedRisksIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getSelectedRisks()).thenReturn(null);
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = emptySelectedRisksValidation.validate(agreement);
        assertTrue(errorOpt.isPresent());
        assertEquals(validationError, errorOpt.get());
    }
    @Test
    public void shouldReturnErrorWhenSelectedRisksIsEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getSelectedRisks()).thenReturn(List.of());
        ValidationErrorDTO validationError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_8")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOpt = emptySelectedRisksValidation.validate(agreement);
        assertTrue(errorOpt.isPresent());
        assertEquals(validationError, errorOpt.get());
    }
    @Test
    public void shouldNotReturnErrorWhenSelectedRisksIsNotEmpty() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getSelectedRisks()).thenReturn(List.of("Travel_MEDICAL", "Travel_LUGGAGE"));
        Optional<ValidationErrorDTO> errorOpt = emptySelectedRisksValidation.validate(agreement);
        assertTrue(errorOpt.isEmpty());
    }

}