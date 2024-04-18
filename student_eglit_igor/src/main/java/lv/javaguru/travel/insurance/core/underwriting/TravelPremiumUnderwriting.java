package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelPremiumUnderwriting {
    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request);
}
