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
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAgreementDateFromNotLessThanTodayTest {

    @Mock
    private DateTimeUtil dateTimeUtil;
    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateFromNotLessThanToday validate;

    @InjectMocks
    @Autowired
    private SetUpInstancesHelper helper;

    @Test
    public void validate_ShouldReturnErrorWhenAgreementDateFromLessThanToday() {
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                "LEVEL_10000",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);

        when(dateTimeUtil.startOfToday()).thenReturn(new Date(2025 - 1900, 3, 11));
        when(errorMock.buildError("ERROR_CODE_11"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_11",
                        "Field agreementDateFrom is in the past!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_11", result.get().errorCode());
        assertEquals("Field agreementDateFrom is in the past!", result.get().description());
    }

}