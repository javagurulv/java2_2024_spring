package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);

}
