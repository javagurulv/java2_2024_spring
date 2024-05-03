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

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAgreementDateChronologyTest {

    @Mock
    private AgreementDTO agreementMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateChronology validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpAgreementValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpAgreementMockWithValues(agreementMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsEqualsAgreementDateFrom() {
        // agreementMock.getAgreementDateFrom() returns (new Date (2025 - 1900, 2, 10))
        when(agreementMock.getAgreementDateTo()).thenReturn(new Date(2025 - 1900, 2, 10));
        when(errorMock.buildError("ERROR_CODE_13"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_13",
                        "AgreementDateTo must be after AgreementDateFrom!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_13", result.get().getErrorCode());
        assertEquals("AgreementDateTo must be after AgreementDateFrom!", result.get().getDescription());
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsLessThanAgreementDateFrom() {
        // agreementMock.getAgreementDateFrom() returns (new Date (2025 - 1900, 2, 10))
        when(agreementMock.getAgreementDateTo()).thenReturn(new Date(2025 - 1900, 2, 9));
        when(errorMock.buildError("ERROR_CODE_13"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_13",
                        "AgreementDateTo must be after AgreementDateFrom!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_13", result.get().getErrorCode());
        assertEquals("AgreementDateTo must be after AgreementDateFrom!", result.get().getDescription());
    }

}