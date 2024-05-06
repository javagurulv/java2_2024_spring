package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculateRiskPremium(AgreementDTO agreement, PersonDTO person);

    String getRiskIc();

}
