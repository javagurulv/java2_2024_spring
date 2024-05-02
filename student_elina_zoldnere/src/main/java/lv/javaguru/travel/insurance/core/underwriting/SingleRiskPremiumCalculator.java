package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class SingleRiskPremiumCalculator {

    @Autowired
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    RiskPremium calculatePremium(String riskIc, TravelCalculatePremiumRequestV1 request) {
        var riskPremiumCalculator = findRiskPremiumCalculator(riskIc);
        BigDecimal premium = riskPremiumCalculator.calculateRiskPremium(request);
        return new RiskPremium(riskIc, premium);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
