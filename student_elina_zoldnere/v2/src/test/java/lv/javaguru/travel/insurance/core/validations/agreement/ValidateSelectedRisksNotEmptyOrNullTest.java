package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidateSetUpAgreementValuesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateSelectedRisksNotEmptyOrNullTest {

    @Mock
    private AgreementDTO agreementMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateSelectedRisksNotEmptyOrNull validateRisks;

    @Autowired
    @InjectMocks
    private ValidateSetUpAgreementValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpAgreementMockWithValues(agreementMock);
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenSelectedRisksIsNull() {
        when(agreementMock.getSelectedRisks()).thenReturn(null);
        when(errorFactoryMock.buildError("ERROR_CODE_5"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_5", "Field selectedRisks is empty!"));

        Optional<ValidationErrorDTO> result = validateRisks.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().getErrorCode());
        assertEquals("Field selectedRisks is empty!", result.get().getDescription());
    }

    @Test
    public void validateSingle_ShouldReturnErrorWhenSelectedRisksIsEmpty() {
        when(agreementMock.getSelectedRisks()).thenReturn(Collections.emptyList());
        when(errorFactoryMock.buildError("ERROR_CODE_5"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_5", "Field selectedRisks is empty!"));

        Optional<ValidationErrorDTO> result = validateRisks.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().getErrorCode());
        assertEquals("Field selectedRisks is empty!", result.get().getDescription());
    }

}