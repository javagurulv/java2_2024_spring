package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTOBuilder;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTOBuilder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumUnderwritingImplTest {

    @Mock
    private SingleRiskPremiumCalculator singleRiskCalculatorMock;
    @Mock
    private TotalRiskPremiumCalculator totalRiskCalculatorMock;

    @InjectMocks
    private TravelCalculatePremiumUnderwritingImpl calculateUnderwriting;

    @ParameterizedTest(name = "{0}")
    @MethodSource("selectedRiskValues")
    void calculateAgreementPremium_ShouldReturnCorrectResult(
            String testName, List<String> selectedRisks, BigDecimal expectedPremium, int expectedRiskDTOCount) {
        PersonDTO person = PersonDTOBuilder.createPerson().build();
        AgreementDTO agreement = AgreementDTOBuilder.createAgreement()
                .withSelectedRisks(selectedRisks)
                .build();
        RiskDTO risk = RiskDTOBuilder.createRisk().build();

        when(singleRiskCalculatorMock.calculatePremium(any(), eq(agreement), eq(person)))
                .thenReturn(risk);
        when(totalRiskCalculatorMock.calculatePremium(anyList())).thenReturn(BigDecimal.TEN);

        TravelPremiumCalculationResult result = calculateUnderwriting.calculateAgreementPremium(agreement, person);

        assertThat(result)
                .satisfies(r -> {
                    assertThat(r.getAgreementPremium()).isEqualTo(expectedPremium);
                    assertThat(r.getRisks().size()).isEqualTo(expectedRiskDTOCount);
                });
    }

    private static Stream<Arguments> selectedRiskValues() {
        return Stream.of(
                Arguments.of("One selected risk", List.of("TRAVEL_MEDICAL"), BigDecimal.TEN, 1),
                Arguments.of("Two selected risks risks", List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"),
                        BigDecimal.TEN, 2)
        );
    }

}