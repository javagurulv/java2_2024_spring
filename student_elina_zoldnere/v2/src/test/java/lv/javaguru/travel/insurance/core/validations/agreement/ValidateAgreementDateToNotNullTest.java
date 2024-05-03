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
class ValidateAgreementDateToNotNullTest {

    @Mock
    private AgreementDTO agreementMock;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateToNotNull validate;

    @Autowired
    @InjectMocks
    private ValidateSetUpAgreementValuesHelper helper;

    @BeforeEach
    public void setUp() {
        helper.setUpAgreementMockWithValues(agreementMock);
    }

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToIsNull() {
        when(agreementMock.getAgreementDateTo()).thenReturn(null);
        when(errorMock.buildError("ERROR_CODE_4"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_4", "Field agreementDateTo is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreementMock);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_4", result.get().getErrorCode());
        assertEquals("Field agreementDateTo is empty!", result.get().getDescription());
    }

}