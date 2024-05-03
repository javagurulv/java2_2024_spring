package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SingleRiskPremiumCalculatorTest {

    @Mock
    private TravelCalculatePremiumRequestV1 requestMock;
    @Mock
    private TravelRiskPremiumCalculator calculatorMock1;
    @Mock
    private TravelRiskPremiumCalculator calculatorMock2;
    @Mock
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    @InjectMocks
    private SingleRiskPremiumCalculator singleRiskCalculator;

    @BeforeEach
    void setUp() {
        when(riskPremiumCalculators.stream()).thenReturn(Stream.of(calculatorMock1, calculatorMock2));
        when(calculatorMock1.getRiskIc()).thenReturn("TRAVEL_CANCELLATION");
        when(calculatorMock2.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
    }

    @Test
    void calculatePremium_ShouldReturnRiskPremium() {
        String riskIc = "TRAVEL_MEDICAL";
        when(calculatorMock2.calculateRiskPremium(requestMock)).thenReturn(BigDecimal.ONE);

        RiskPremium result = singleRiskCalculator.calculatePremium(riskIc, requestMock);

        assertEquals("TRAVEL_MEDICAL", result.getRiskIc());
        assertEquals(BigDecimal.ONE, result.getPremium());
    }

    @Test
    void calculatePremium_ThrowsRuntimeExceptionWhenOnlyNotSupportedRisk() {
        String riskIc = "NOT_SUPPORTED_RISK";

        assertThrows(RuntimeException.class, () -> singleRiskCalculator.calculatePremium(riskIc, requestMock));
    }

}
