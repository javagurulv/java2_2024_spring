package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class CalculateAndUpdatePersonsWithRiskPremiums {

    @Autowired
    private TravelCalculatePremiumUnderwriting calculateUnderwriting;

    List<PersonDTO> calculateRiskPremiumsForAllPersons(AgreementDTO agreement) {
        List<PersonDTO> personsWithRisks = new ArrayList<>();

        agreement.persons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult =
                    calculateUnderwriting.calculateAgreementPremium(agreement, person);

            // new person instance!
            PersonDTO personWithRisks = person.withRisks(calculationResult.getRisks());
            personsWithRisks.add(personWithRisks);
        });
        return personsWithRisks;
    }
}
