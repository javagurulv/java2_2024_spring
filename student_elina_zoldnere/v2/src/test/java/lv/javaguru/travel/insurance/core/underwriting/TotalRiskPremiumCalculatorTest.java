package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
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
    private List<RiskDTO> listRiskMock;
    @InjectMocks
    private TotalRiskPremiumCalculator totalRiskCalculator;

    @Test
    void calculatePremium_ShouldReturnCorrectResult() {
        RiskDTO risk1 = new RiskDTO("TRAVEL_MEDICAL", BigDecimal.ONE);
        RiskDTO risk2 = new RiskDTO("TRAVEL_MEDICAL", BigDecimal.TEN);
        when(listRiskMock.stream()).thenReturn(Stream.of(risk1, risk2));

        BigDecimal result = totalRiskCalculator.calculatePremium(listRiskMock);
        assertEquals(BigDecimal.valueOf(11), result);
    }

}
