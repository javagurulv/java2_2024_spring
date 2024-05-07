package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

public interface TravelCalculatePremiumUnderwriting {

    TravelPremiumCalculationResult calculateAgreementPremium(AgreementDTO agreement, PersonDTO person);

}
