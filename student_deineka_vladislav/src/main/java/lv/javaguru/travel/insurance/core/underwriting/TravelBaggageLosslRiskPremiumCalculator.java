package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelBaggageLosslRiskPremiumCalculator implements TravelRiskPremiumCalculator {


    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest premiumRequest) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_BAGGAGE_LOSS";
    }
}
