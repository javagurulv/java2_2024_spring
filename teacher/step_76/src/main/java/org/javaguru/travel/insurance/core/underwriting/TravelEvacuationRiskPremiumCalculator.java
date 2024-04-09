package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelEvacuationRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_EVACUATION";
    }

}
