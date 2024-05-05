package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    @Autowired
    private List<TravelRiskPremiumCalculator> travelRiskPremiumCalculators;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return travelRiskPremiumCalculators.stream()
                .map(travelRiskPremiumCalculator -> travelRiskPremiumCalculator.calculatePremium(request))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}