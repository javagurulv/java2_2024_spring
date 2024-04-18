package lv.javaguru.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    @Autowired
    private List<TravelRiskPremiumCalculator> travelRiskPremiumCalculator;

    @Override
    public TravelPremiumCalculationResult calculateAgreementPremium(TravelCalculatePremiumRequest request) {
        List<RiskPremium> riskPremiums = request.getSelectedRisks().stream()
                .map(riskIc -> {
                    BigDecimal riskPremium = calculatePremiumForRisk(riskIc, request);
                    return new RiskPremium(riskIc, riskPremium);
                })
                .toList();
        BigDecimal totalPremium = riskPremiums.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);

    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequest request) {
        return travelRiskPremiumCalculator.stream()
                .filter(riskOutOfTypeCalculator -> riskOutOfTypeCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc))
                .calculatePremium(request);
    }
}
