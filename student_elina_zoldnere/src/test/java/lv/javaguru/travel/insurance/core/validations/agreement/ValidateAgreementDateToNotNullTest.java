package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.validations.ValidateSetUpRequestHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
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
class ValidateAgreementDateToNotNullTest {

    @Mock
    private TravelCalculatePremiumRequest requestMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateToNotNull validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpRequestHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpRequestMockWithAllValues(requestMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsNull() {
        when(requestMock.getAgreementDateTo()).thenReturn(null);
        when(errorMock.buildError("ERROR_CODE_4"))
                .thenReturn(new ValidationError("ERROR_CODE_4", "Field agreementDateTo is empty!"));

        Optional<ValidationError> result = validate.validateSingle(requestMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_4", result.get().getErrorCode());
        assertEquals("Field agreementDateTo is empty!", result.get().getDescription());
    }

}