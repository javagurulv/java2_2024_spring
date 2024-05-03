package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
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
class ValidateAgreementDateToNotLessThanTodayTest {

    @Mock
    private AgreementDTO agreementMock;
    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateToNotLessThanToday validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpAgreementValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpAgreementMockWithValues(agreementMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToLessThanToday() {
        when(agreementMock.getAgreementDateTo()).thenReturn(new Date(2025 - 1900, 2, 11));
        when(dateTimeUtil.startOfToday()).thenReturn(new Date(2025 - 1900, 3, 11));
        when(errorMock.buildError("ERROR_CODE_12"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_12", "Field agreementDateTo is in the past!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_12", result.get().getErrorCode());
        assertEquals("Field agreementDateTo is in the past!", result.get().getDescription());
    }

}