package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumUnderwritingImpl implements TravelCalculatePremiumUnderwriting {

    @Autowired
    List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    @Override
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        List<String> selectedRisks = request.getSelectedRisks();

        return selectedRisks.stream()
                .map(riskIc -> calculateRiskPremium(riskIc, request))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateRiskPremium(String riskIc, TravelCalculatePremiumRequest request) {
        var riskPremiumCalculator = findRiskPremiumCalculator(riskIc);
        return riskPremiumCalculator.calculateRiskPremium(request);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
