package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    @Autowired
    private SelectedRisksPremiumCalculator riskPremiumCalculators;

    @Override
    public TravelPremiumCalculationResult calculationPremium(TravelCalculatePremiumRequest premiumRequest) {
        List<RiskPremium> riskPremiums = selectedRiskPremiumCalculation(premiumRequest);
        BigDecimal totalPremium = totalPremiumCalculation(riskPremiums);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private List<RiskPremium> selectedRiskPremiumCalculation(TravelCalculatePremiumRequest premiumRequest) {
        return riskPremiumCalculators.calculationPremium(premiumRequest);
    }

    private static  BigDecimal totalPremiumCalculation(List<RiskPremium> riskPremiums) {
        return riskPremiums.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }






}
