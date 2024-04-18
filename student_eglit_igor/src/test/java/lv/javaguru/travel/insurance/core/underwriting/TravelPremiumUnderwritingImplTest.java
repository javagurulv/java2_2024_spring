package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TravelPremiumUnderwritingImplTest {

    @Mock
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;
    @InjectMocks
    private TravelPremiumUnderwritingImpl underwriting;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateTotalPremiumForAllRisks() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        List<RiskPremium> riskPremiums = List.of(new RiskPremium("risk1", BigDecimal.TEN), new RiskPremium("risk2", BigDecimal.ONE));
        when(selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request)).thenReturn(riskPremiums);

        TravelPremiumCalculationResult result = underwriting.calculateAgreementPremium(request);

        assertEquals(new BigDecimal("11"), result.getTotalPremium());
        assertEquals(riskPremiums, result.getRiskPremiums());
    }

    @Test
    void shouldReturnZeroPremiumWhenNoRisksSelected() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        when(selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request)).thenReturn(Collections.emptyList());

        TravelPremiumCalculationResult result = underwriting.calculateAgreementPremium(request);

        assertEquals(BigDecimal.ZERO, result.getTotalPremium());
        assertEquals(Collections.emptyList(), result.getRiskPremiums());
    }
}