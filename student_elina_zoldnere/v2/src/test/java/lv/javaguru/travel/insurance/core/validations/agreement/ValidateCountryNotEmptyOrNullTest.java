package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateCountryNotEmptyOrNullTest {

    @Mock
    private AgreementDTO agreementMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateCountryNotEmptyOrNull validateCountry;

    @Autowired
    @InjectMocks
    private ValidateSetUpAgreementValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpAgreementMockWithValues(agreementMock);
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenCountryIsNull() {
        when(agreementMock.getCountry()).thenReturn(null);
        when(errorFactoryMock.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_6", "Field country is empty!"));

        Optional<ValidationErrorDTO> result = validateCountry.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field country is empty!", result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenCountryIsEmpty() {
        when(agreementMock.getCountry()).thenReturn("");
        when(errorFactoryMock.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_6", "Field country is empty!"));

        Optional<ValidationErrorDTO> result = validateCountry.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field country is empty!", result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenCountryIsBlank() {
        when(agreementMock.getCountry()).thenReturn("     ");
        when(errorFactoryMock.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_6", "Field country is empty!"));

        Optional<ValidationErrorDTO> result = validateCountry.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_6", result.get().getErrorCode());
        assertEquals("Field country is empty!", result.get().getDescription());
    }

}