package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(TravelCalculatePremiumRequest premiumRequest);

    String getRiskIc();

}
