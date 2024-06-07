package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;
    private TravelRiskPremiumCalculator firstRiskPremiumCalculator;
    private TravelRiskPremiumCalculator secondRiskPremiumCalculator;

    @BeforeEach
    public void initialization() {
        firstRiskPremiumCalculator = mock(TravelRiskPremiumCalculator.class);
        secondRiskPremiumCalculator = mock(TravelRiskPremiumCalculator.class);
        var riskPremiumCalculators = List.of(firstRiskPremiumCalculator, secondRiskPremiumCalculator);
        ReflectionTestUtils.setField(premiumUnderwriting, "riskPremiumCalculators", riskPremiumCalculators);

    }

    @Test
    void checkPremiumCalculationForOneRisk() {
        when(firstRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(firstRiskPremiumCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);

        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        TravelPremiumCalculationResult premiumCalculationResult = premiumUnderwriting.calculationPremium(travelCalculatePremiumRequest);
        assertEquals(premiumCalculationResult.getTotalPremium(), BigDecimal.ONE);
    }

    @Test
    void checkPremiumCalculationForTwoRisks() {
        when(firstRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(secondRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_EVACUATION");
        when(firstRiskPremiumCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(secondRiskPremiumCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);

        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_EVACUATION"));
        TravelPremiumCalculationResult premiumCalculationResult = premiumUnderwriting.calculationPremium(travelCalculatePremiumRequest);
        assertEquals(premiumCalculationResult.getTotalPremium(), new BigDecimal(2));
    }


}

