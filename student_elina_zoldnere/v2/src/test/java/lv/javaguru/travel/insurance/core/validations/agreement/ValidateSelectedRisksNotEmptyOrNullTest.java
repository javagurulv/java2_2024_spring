package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateSelectedRisksNotEmptyOrNullTest {

    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateSelectedRisksNotEmptyOrNull validateRisks;
    @InjectMocks
    private SetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("selectedRisksValues")
    public void validateSingle_ShouldReturnErrorWhenSelectedRisksAreNotValid(
            String testName, List<String> selectedRisks) {
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(selectedRisks)
                .withPerson(helper.newPersonDTO())
                .withPremium(BigDecimal.ZERO)
                .build();

        when(errorFactoryMock.buildError("ERROR_CODE_5"))
                .thenReturn(new ValidationErrorDTO("ERROR_CODE_5", "Field selectedRisks is empty!"));

        Optional<ValidationErrorDTO> result = validateRisks.validateSingle(agreement);

        assertTrue(result.isPresent());
        assertEquals("ERROR_CODE_5", result.get().errorCode());
        assertEquals("Field selectedRisks is empty!", result.get().description());
    }

    private static Stream<Arguments> selectedRisksValues() {
        return Stream.of(
                Arguments.of("selected risks null", null),
                Arguments.of("selected risks empty", Collections.emptyList())
        );
    }

}