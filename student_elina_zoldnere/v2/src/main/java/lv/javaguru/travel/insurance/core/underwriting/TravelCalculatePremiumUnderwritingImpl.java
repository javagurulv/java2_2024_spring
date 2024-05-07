package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
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
    public TravelPremiumCalculationResult calculateAgreementPremium(AgreementDTO agreement, PersonDTO person) {
        List<String> selectedRisks = agreement.selectedRisks();

        List<RiskDTO> risks = selectedRisks.stream()
                .map(riskIc -> singleRiskCalculator.calculatePremium(riskIc, agreement, person))
                .toList();

        BigDecimal agreementPremium = totalRiskCalculator.calculatePremium(risks);

        return new TravelPremiumCalculationResult(agreementPremium, risks);
    }

}
