package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculationPremium(TravelCalculatePremiumRequest premiumRequest);
}
