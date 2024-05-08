package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CalculateAndUpdateAgreementWithPremiums {

    @Autowired
    private CalculateAndUpdatePersonsWithRiskPremiums calculateAndUpdatePersons;
    @Autowired
    private CalculateTotalAgreementPremium calculateTotalPremium;

    AgreementDTO calculateAgreementPremiums(AgreementDTO agreement) {
        List<PersonDTO> personsWithRiskPremiums = calculateAndUpdatePersons.calculateRiskPremiumsForAllPersons(agreement);
        BigDecimal totalAgreementPremium = calculateTotalPremium.calculateTotalAgreementPremium(personsWithRiskPremiums);
        // new agreement instance!
        return agreement.withPremium(personsWithRiskPremiums, totalAgreementPremium);
    }

}
