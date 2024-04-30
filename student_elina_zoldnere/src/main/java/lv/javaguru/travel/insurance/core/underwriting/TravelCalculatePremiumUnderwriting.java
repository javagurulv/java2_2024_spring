package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

public interface TravelCalculatePremiumUnderwriting {

    TravelPremiumCalculationResult calculateAgreementPremium(TravelCalculatePremiumRequest request);

}
