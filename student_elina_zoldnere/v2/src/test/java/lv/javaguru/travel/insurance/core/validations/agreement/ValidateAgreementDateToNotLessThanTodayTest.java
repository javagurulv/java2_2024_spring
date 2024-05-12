package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAgreementDateToNotLessThanTodayTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateToNotLessThanToday validate;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateToLessThanToday() {
        AgreementDTO agreement = new AgreementDTO(
                helper.newDate("2025.03.10"),
                helper.newDate("2025.03.11"),
                "SPAIN",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);
        when(dateTimeUtil.startOfToday()).thenReturn(helper.newDate("2025.04.11"));
        when(errorMock.buildError("ERROR_CODE_12"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_12",
                        "Field agreementDateTo is in the past!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_12", result.get().errorCode());
        assertEquals("Field agreementDateTo is in the past!", result.get().description());
    }

}