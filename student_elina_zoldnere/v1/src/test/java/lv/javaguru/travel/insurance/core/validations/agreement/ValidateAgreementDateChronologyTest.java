package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.validations.ValidateSetUpRequestHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;

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
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateChronology validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpRequestHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsEqualsAgreementDateFrom() {
        // requestMock.getAgreementDateFrom() returns (new Date (2025 - 1900, 2, 10))
        when(requestMock.getAgreementDateTo()).thenReturn(new Date(2025 - 1900, 2, 10));
        when(errorMock.buildError("ERROR_CODE_13"))
                .thenReturn(new ValidationError("ERROR_CODE_13",
                        "AgreementDateTo must be after AgreementDateFrom!"));

        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_13", result.get().getErrorCode());
        assertEquals("AgreementDateTo must be after AgreementDateFrom!", result.get().getDescription());
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsLessThanAgreementDateFrom() {
        // requestMock.getAgreementDateFrom() returns (new Date (2025 - 1900, 2, 10))
        when(requestMock.getAgreementDateTo()).thenReturn(new Date(2025 - 1900, 2, 9));
        when(errorMock.buildError("ERROR_CODE_13"))
                .thenReturn(new ValidationError("ERROR_CODE_13",
                        "AgreementDateTo must be after AgreementDateFrom!"));

        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_13", result.get().getErrorCode());
        assertEquals("AgreementDateTo must be after AgreementDateFrom!", result.get().getDescription());
    }

}