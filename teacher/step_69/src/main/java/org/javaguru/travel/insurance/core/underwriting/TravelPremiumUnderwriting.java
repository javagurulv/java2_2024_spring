package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelPremiumUnderwriting {

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);

}
