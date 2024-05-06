package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmptyCountryValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    private EmptyCountryValidation validation;

    @Test
    public void shouldReturnErrorWhenCountryIsNull() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getCountry()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(
                new ValidationErrorDTO("ERROR_CODE_10", "Field Country is empty!"));
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isPresent());


    }
    @Test
    public void shouldReturnErrorWhenCountryIsBlank() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getCountry()).thenReturn("");
        ValidationErrorDTO validateError = mock(ValidationErrorDTO.class);
        when(errorFactory.buildError("ERROR_CODE_10")).thenReturn(validateError);
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isPresent());
        assertSame(validateError, errorOpt.get());

    }
    @Test
    public void shouldNotReturnErrorWhenCountryIsNotBlankAndExistInDatabase() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreement.getCountry()).thenReturn("SPAIN");
        Optional<ValidationErrorDTO> errorOpt = validation.validate(agreement);
        assertTrue(errorOpt.isEmpty());
    }

}