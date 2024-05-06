package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidateSetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAgreementDateChronologyTest {

    @Mock
    private ValidationErrorFactory errorMock;

    @InjectMocks
    private ValidateAgreementDateChronology validate;

    @InjectMocks
    @Autowired
    private ValidateSetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("agreementDateToValue")
    public void validate_ShouldReturnErrorWhenAgreementDateChronologyIsWrong(String testName, Date agreementDateTo) {
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                agreementDateTo,
                "SPAIN",
                "LEVEL_10000",
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);

        when(errorMock.buildError("ERROR_CODE_13"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_13",
                        "AgreementDateTo must be after AgreementDateFrom!"));

        Optional<ValidationErrorDTO> result = validate.validateSingle(agreement);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_13", result.get().errorCode());
        assertEquals("AgreementDateTo must be after AgreementDateFrom!", result.get().description());
    }

    private static Stream<Arguments> agreementDateToValue() {
        return Stream.of(
                Arguments.of("agreementDateTo equals agreementDateFrom",
                        new Date(2025 - 1900, 2, 10)),
                Arguments.of("agreementDateTo less than agreementDateFrom",
                        new Date(2025 - 1900, 2, 9))
        );
    }

}