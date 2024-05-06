package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.validations.ValidateSetUpInstancesHelper;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidateSelectedRisksAreInDatabaseTest {

    @Mock
    private ClassifierValueRepository repositoryMock;
    @Mock
    private ValidationErrorFactory errorFactoryMock;

    @InjectMocks
    private ValidateSelectedRisksAreInDatabase validateRisks;

    @Autowired
    @InjectMocks
    private ValidateSetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("riskTypeValues")
    public void validateList_ShouldReturnCorrectResponseWhenSelectedRisksAreNotSupported(
            String testName, String firstRisk, String secondRisk, int expectedValue) {
        AgreementDTO agreement = new AgreementDTO(
                new Date(2025 - 1900, 2, 10),
                new Date(2025 - 1900, 2, 11),
                "SPAIN",
                "LEVEL_10000",
                List.of(firstRisk, secondRisk),
                List.of(helper.newPersonDTO()),
                BigDecimal.ZERO);
        Mockito.lenient().when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "TRAVEL_MEDICAL"))
                .thenReturn(Optional.of(new ClassifierValue()));
        when(repositoryMock.findByClassifierTitleAndIc("RISK_TYPE", "INVALID"))
                .thenReturn(Optional.empty());

        ValidationErrorDTO error = new ValidationErrorDTO("ERROR_CODE_9", "description");
        lenient().when(errorFactoryMock.buildError(eq("ERROR_CODE_9"), anyList())).thenReturn(error);

        List<ValidationErrorDTO> result = validateRisks.validateList(agreement);

        assertEquals(expectedValue, result.size());
    }

    private static Stream<Arguments> riskTypeValues() {
        return Stream.of(
                Arguments.of("one invalid risk", "TRAVEL_MEDICAL", "INVALID", 1),
                Arguments.of("two invalid risks", "INVALID", "INVALID", 2)
        );
    }

}