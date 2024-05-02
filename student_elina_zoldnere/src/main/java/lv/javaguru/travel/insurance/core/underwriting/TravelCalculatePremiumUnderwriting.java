package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

public interface TravelCalculatePremiumUnderwriting {

    TravelPremiumCalculationResult calculateAgreementPremium(TravelCalculatePremiumRequestV1 request);

}
