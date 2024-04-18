package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
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
    public TravelPremiumCalculationResult calculateAgreementPremium(TravelCalculatePremiumRequest request) {
        List<String> selectedRisks = request.getSelectedRisks();

        List<RiskPremium> riskPremiums = selectedRisks.stream()
                .map(riskIc -> calculateRiskPremium(riskIc, request))
                .toList();

        BigDecimal agreementPremium = calculateTotalPremium(riskPremiums);

        return new TravelPremiumCalculationResult(agreementPremium, riskPremiums);
    }

    private RiskPremium calculateRiskPremium(String riskIc, TravelCalculatePremiumRequest request) {
        var riskPremiumCalculator = findRiskPremiumCalculator(riskIc);
        BigDecimal premium = riskPremiumCalculator.calculateRiskPremium(request);
        return new RiskPremium(riskIc, premium);
    }

    private BigDecimal calculateTotalPremium(List<RiskPremium> riskPremiums) {
        return riskPremiums.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
