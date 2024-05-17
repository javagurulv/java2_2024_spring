package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.util.SetUpInstancesHelper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumUnderwritingImplTest {

    @Mock
    private SingleRiskPremiumCalculator singleRiskCalculatorMock;
    @Mock
    private TotalRiskPremiumCalculator totalRiskCalculatorMock;

    @InjectMocks
    private TravelCalculatePremiumUnderwritingImpl calculateUnderwriting;

    @Autowired
    @InjectMocks
    private SetUpInstancesHelper helper;

    @ParameterizedTest(name = "{0}")
    @MethodSource("selectedRiskValues")
    void calculateAgreementPremium_ShouldReturnCorrectResult(
            String testName, List<String> selectedRisks, BigDecimal expectedPremium, int expectedRiskDTOCount) {
        PersonDTO person = helper.newPersonDTO();
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withDateFrom(helper.newDate("2025.03.10"))
                .withDateTo(helper.newDate("2025.03.11"))
                .withCountry("SPAIN")
                .withSelectedRisks(selectedRisks)
                .withPerson(person)
                .withPremium(BigDecimal.ZERO)
                .build();
        when(singleRiskCalculatorMock.calculatePremium(any(), eq(agreement), eq(person)))
                .thenReturn(helper.newRiskDTO());
        when(totalRiskCalculatorMock.calculatePremium(anyList())).thenReturn(BigDecimal.TEN);

        TravelPremiumCalculationResult result = calculateUnderwriting.calculateAgreementPremium(agreement, person);

        assertEquals(expectedPremium, result.getAgreementPremium());
        assertEquals(expectedRiskDTOCount, result.getRisks().size());
    }

    private static Stream<Arguments> selectedRiskValues() {
        return Stream.of(
                Arguments.of("one selected risk", List.of("TRAVEL_MEDICAL"), BigDecimal.TEN, 1),
                Arguments.of("two selected risks risks", List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"),
                        BigDecimal.TEN, 2)
        );
    }

}