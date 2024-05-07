package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class SingleRiskPremiumCalculator {

    @Autowired
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    RiskDTO calculatePremium(String riskIc, AgreementDTO agreement, PersonDTO person) {
        var riskPremiumCalculator = findRiskPremiumCalculator(riskIc);
        BigDecimal premium = riskPremiumCalculator.calculateRiskPremium(agreement, person);
        return new RiskDTO(riskIc, premium);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
