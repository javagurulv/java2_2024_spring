package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculationPremium(TravelCalculatePremiumRequest premiumRequest);
}
