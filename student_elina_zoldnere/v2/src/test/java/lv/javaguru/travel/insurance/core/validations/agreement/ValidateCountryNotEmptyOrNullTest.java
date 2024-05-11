package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateCountryNotEmptyOrNullTest {

    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateCountryNotEmptyOrNull validateCountry;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("countryValue")
    public void validateSingle_ShouldReturnErrorWhenCountryIsNotValid(String testName, String country) {
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                country,
                List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE"),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);
        when(errorFactoryMock.buildError("ERROR_CODE_6"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_6", "Field country is empty!"));

        Optional<ValidationErrorDTO> result = validateCountry.validateSingle(agreement);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_6", result.get().errorCode());
        assertEquals("Field country is empty!", result.get().description());
    }

    private static Stream<Arguments> countryValue() {
        return Stream.of(
                Arguments.of("country null", null),
                Arguments.of("country empty", ""),
                Arguments.of("country blank", "     ")
        );
    }

}