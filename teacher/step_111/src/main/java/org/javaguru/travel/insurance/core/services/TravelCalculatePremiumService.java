package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumResponseV1;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request);

}
