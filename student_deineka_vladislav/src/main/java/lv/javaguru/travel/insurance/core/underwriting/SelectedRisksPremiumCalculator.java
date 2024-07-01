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
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    List<RiskPremium> calculationPremium(TravelCalculatePremiumRequest premiumRequest) {
        return premiumRequest.getSelectedRisks()
                .stream()
                .map(riskIc -> new RiskPremium(riskIc, calculatePremiumForRisk(riskIc, premiumRequest)))
                .toList();
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequest premiumRequest) {
        return findRiskPremiumCalculator(riskIc).calculatePremium(premiumRequest);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("riskIc is not supported = " + riskIc));
    }
}
