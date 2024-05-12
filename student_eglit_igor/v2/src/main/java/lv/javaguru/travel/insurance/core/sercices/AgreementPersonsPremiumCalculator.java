package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class AgreementPersonsPremiumCalculator {
    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;

    void calculateRiskPremiums(AgreementDTO agreement) {
        List<PersonDTO> updatedPersons = new ArrayList<>();
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult = premiumUnderwriting.calculatePremium(agreement, person);
            PersonDTO updatedPerson = new PersonDTO(person.personFirstName(),
                    person.personLastName(),
                    person.personBirthDate(),
                    calculationResult.getRisks());
            updatedPersons.add(updatedPerson);
        });
        agreement.setPersons(updatedPersons);
    }
}
