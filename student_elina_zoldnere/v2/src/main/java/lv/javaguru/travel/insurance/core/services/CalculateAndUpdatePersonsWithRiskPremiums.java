package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class CalculateAndUpdatePersonsWithRiskPremiums {

    @Autowired
    private TravelCalculatePremiumUnderwriting calculateUnderwriting;

    List<PersonDTO> calculateRiskPremiumsForAllPersons(AgreementDTO agreement) {
        return agreement.persons().stream()
                .map(person -> calculateRiskPremiumsForOnePerson(agreement, person))
                .toList();
    }

    private PersonDTO calculateRiskPremiumsForOnePerson(AgreementDTO agreement, PersonDTO person) {
        TravelPremiumCalculationResult calculationResult =
                calculateUnderwriting.calculateAgreementPremium(agreement, person);
        return person.withRisks(calculationResult.getRisks());
    }

}
