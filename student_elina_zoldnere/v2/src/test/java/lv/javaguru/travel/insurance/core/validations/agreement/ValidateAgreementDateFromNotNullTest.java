package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAgreementDateFromNotNullTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateFromNotNull validate;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromIsNull() {
        AgreementDTO agreement = new AgreementDTO(
                null,
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                "LEVEL_10000",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);
        when(errorMock.buildError("ERROR_CODE_3"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_3", "Field agreementDateFrom is empty!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_3", result.get().errorCode());
        assertEquals("Field agreementDateFrom is empty!", result.get().description());
    }

}