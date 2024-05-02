package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumUnderwritingImplTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private SingleRiskPremiumCalculator singleRiskCalculatorMock;
    @Mock
    private TotalRiskPremiumCalculator totalRiskCalculatorMock;
    @Mock
    private RiskPremium riskPremiumMock1;
    @Mock
    private RiskPremium riskPremiumMock2;

    @InjectMocks
    private TravelCalculatePremiumUnderwritingImpl calculateUnderwriting;

    @Test
    void calculateAgreementPremium_ShouldReturnCorrectResultForOneValidRisk() {
        when(requestMock.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        when(singleRiskCalculatorMock.calculatePremium("TRAVEL_MEDICAL", requestMock))
                .thenReturn(riskPremiumMock1);
        when(totalRiskCalculatorMock.calculatePremium(List.of(riskPremiumMock1))).thenReturn(BigDecimal.ONE);

        TravelPremiumCalculationResult result = calculateUnderwriting.calculateAgreementPremium(requestMock);

        assertEquals(BigDecimal.ONE, result.getAgreementPremium());
        assertEquals(1, result.getRiskPremiums().size());
    }

    @Test
    void calculateAgreementPremium_ShouldReturnCorrectResultForTwoValidRisks() {
        when(requestMock.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_CANCELLATION"));
        when(singleRiskCalculatorMock.calculatePremium("TRAVEL_MEDICAL", requestMock))
                .thenReturn(riskPremiumMock1);
        when(singleRiskCalculatorMock.calculatePremium("TRAVEL_CANCELLATION", requestMock))
                .thenReturn(riskPremiumMock2);
        when(totalRiskCalculatorMock.calculatePremium(List.of(riskPremiumMock1, riskPremiumMock2)))
                .thenReturn(BigDecimal.TEN);

        TravelPremiumCalculationResult result = calculateUnderwriting.calculateAgreementPremium(requestMock);

        assertEquals(BigDecimal.TEN, result.getAgreementPremium());
        assertEquals(2, result.getRiskPremiums().size());
    }

}