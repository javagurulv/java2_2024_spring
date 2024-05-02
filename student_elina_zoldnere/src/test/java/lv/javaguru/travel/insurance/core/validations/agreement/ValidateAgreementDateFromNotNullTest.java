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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAgreementDateFromNotNullTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateFromNotNull validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpRequestHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromIsNull() {
        when(requestMock.getAgreementDateFrom()).thenReturn(null);
        when(errorMock.buildError("ERROR_CODE_3"))
                .thenReturn(new ValidationError("ERROR_CODE_3", "Field agreementDateFrom is empty!"));

        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_3", result.get().getErrorCode());
        assertEquals("Field agreementDateFrom is empty!", result.get().getDescription());
    }

}