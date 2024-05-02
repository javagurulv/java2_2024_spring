package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumUnderwritingImpl implements TravelCalculatePremiumUnderwriting {

    @Autowired
    private SingleRiskPremiumCalculator singleRiskCalculator;
    @Autowired
    private TotalRiskPremiumCalculator totalRiskCalculator;

    @Override
    public TravelPremiumCalculationResult calculateAgreementPremium(TravelCalculatePremiumRequestV1 request) {
        List<String> selectedRisks = request.getSelectedRisks();

        List<RiskPremium> riskPremiums = selectedRisks.stream()
                .map(riskIc -> singleRiskCalculator.calculatePremium(riskIc, request))
                .toList();

        BigDecimal agreementPremium = totalRiskCalculator.calculatePremium(riskPremiums);

        return new TravelPremiumCalculationResult(agreementPremium, riskPremiums);
    }

}
