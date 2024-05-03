package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TotalRiskPremiumCalculatorTest {

    @Mock
    private RiskPremium riskPremiumMock1;
    @Mock
    private RiskPremium riskPremiumMock2;
    @Mock
    private List<RiskPremium> listRiskPremiumMock;
    @InjectMocks
    private TotalRiskPremiumCalculator totalRiskCalculator;

    @Test
    void calculatePremium_ShouldReturnCorrectResult() {
        when(listRiskPremiumMock.stream()).thenReturn(Stream.of(riskPremiumMock1, riskPremiumMock2));
        when(riskPremiumMock1.getPremium()).thenReturn(BigDecimal.ONE);
        when(riskPremiumMock2.getPremium()).thenReturn(BigDecimal.TEN);

        BigDecimal result = totalRiskCalculator.calculatePremium(listRiskPremiumMock);
        assertEquals(BigDecimal.valueOf(11), result);
    }

}
