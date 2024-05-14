package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class SelectedRisksPremiumCalculator {

    @Autowired
    private List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    List<RiskPremium> calculateSelectedRisksPremiums(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(riskIc -> {
                    BigDecimal riskPremium = calculateSingleRiskPremium(riskIc, request);
                    return new RiskPremium(riskIc, riskPremium);
                })
                .toList();
    }

    private BigDecimal calculateSingleRiskPremium(String riskIc, TravelCalculatePremiumRequest request) {
        var riskPremiumCalculator = getCurrentRiskCalculator(riskIc);
        return riskPremiumCalculator.calculatePremium(request);
    }

    private TravelRiskPremiumCalculator getCurrentRiskCalculator(String riskIc) {
        return travelRiskPremiumCalculators.stream()
                .filter(travelRiskPremiumCalculator -> travelRiskPremiumCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported risk " + riskIc));
    }

}