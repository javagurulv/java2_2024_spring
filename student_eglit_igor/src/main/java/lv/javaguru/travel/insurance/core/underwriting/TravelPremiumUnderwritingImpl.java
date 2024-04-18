package lv.javaguru.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private List<TravelRiskPremiumCalculator> travelRiskPremiumCalculator;

    @Override
    public BigDecimal calculateAgreementPremium(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(riskIc -> calculatePremiumForRisk(String.valueOf(riskIc), request))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequest request) {
        return travelRiskPremiumCalculator.stream()
                .filter(riskOutOfTypeCalculator -> riskOutOfTypeCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc))
                .calculatePremium(request);
    }
}
