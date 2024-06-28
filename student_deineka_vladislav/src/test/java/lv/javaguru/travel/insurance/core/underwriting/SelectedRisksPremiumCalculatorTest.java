package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SelectedRisksPremiumCalculatorTest {

        @InjectMocks
        private SelectedRisksPremiumCalculator premiumCalculator;
        private TravelRiskPremiumCalculator firstRiskPremiumCalculator;
        private TravelRiskPremiumCalculator secondRiskPremiumCalculator;

        @BeforeEach
        public void initialization() {
            firstRiskPremiumCalculator = mock(TravelRiskPremiumCalculator.class);
            secondRiskPremiumCalculator = mock(TravelRiskPremiumCalculator.class);
            var riskPremiumCalculators = List.of(firstRiskPremiumCalculator, secondRiskPremiumCalculator);
            ReflectionTestUtils.setField(premiumCalculator, "riskPremiumCalculators", riskPremiumCalculators);

        }

        @Test
        void checkPremiumCalculationForOneRisk() {
            when(firstRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
            when(firstRiskPremiumCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);

            TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
            when(travelCalculatePremiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
            List<RiskPremium> riskPremiums = premiumCalculator.calculationPremium(travelCalculatePremiumRequest);
            assertEquals(riskPremiums.size(), 1);
            assertEquals(riskPremiums.get(0).getRiskIc(), "TRAVEL_MEDICAL");
            assertEquals(riskPremiums.get(0).getPremium(), BigDecimal.ONE);
        }

        @Test
        void checkPremiumCalculationForTwoRisks() {
            when(firstRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
            when(secondRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_EVACUATION");
            when(firstRiskPremiumCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);
            when(secondRiskPremiumCalculator.calculatePremium(any())).thenReturn(BigDecimal.ONE);

            TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
            when(premiumRequest.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_EVACUATION"));
            List<RiskPremium> riskPremiums = premiumCalculator.calculationPremium(premiumRequest);
            assertEquals(riskPremiums.size(), 2);
            assertEquals(riskPremiums.get(0).getRiskIc(), "TRAVEL_MEDICAL");
            assertEquals(riskPremiums.get(0).getPremium(), BigDecimal.ONE);
            assertEquals(riskPremiums.get(1).getRiskIc(), "TRAVEL_EVACUATION");
            assertEquals(riskPremiums.get(1).getPremium(), BigDecimal.ONE);

        }

        @Test
        void exceptionWhenSelectedRiskIsNotSupported() {
            when(firstRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
            when(secondRiskPremiumCalculator.getRiskIc()).thenReturn("TRAVEL_EVACUATION");

            TravelCalculatePremiumRequest premiumRequest = mock(TravelCalculatePremiumRequest.class);
            when(premiumRequest.getSelectedRisks()).thenReturn(List.of("NOT_SUPPORTED_RISK_TYPE"));
            RuntimeException runtimeException = assertThrows(RuntimeException.class,() -> premiumCalculator.calculationPremium(premiumRequest));
            assertEquals(runtimeException.getMessage(), "riskIc is not supported = NOT_SUPPORTED_RISK_TYPE");
        }


    }
