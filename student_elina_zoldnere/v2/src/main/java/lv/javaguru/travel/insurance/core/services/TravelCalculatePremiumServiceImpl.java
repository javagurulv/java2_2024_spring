package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelCalculatePremiumUnderwriting;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelAgreementValidator agreementValidator;
    @Autowired
    private TravelCalculatePremiumUnderwriting calculateUnderwriting;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        return errors.isEmpty()
                ? buildPremiumResponse(command.getAgreement())
                : buildErrorResponse(errors);
    }

    private TravelCalculatePremiumCoreResult buildPremiumResponse(AgreementDTO agreement) {
        List<PersonDTO> personsWithRiskPremiums = calculateRiskPremiumsForAllPersons(agreement);

        BigDecimal totalAgreementPremium = calculateTotalAgreementPremium(personsWithRiskPremiums);
        // new agreement instance!
        AgreementDTO agreementWithPremium = agreement.withPremium(personsWithRiskPremiums, totalAgreementPremium);

        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreementWithPremium);
        return coreResult;
    }

    private TravelCalculatePremiumCoreResult buildErrorResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private List<PersonDTO> calculateRiskPremiumsForAllPersons(AgreementDTO agreement) {
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

    private BigDecimal calculateTotalAgreementPremium(List<PersonDTO> updatedPersons) {
        return updatedPersons.stream()
                .map(PersonDTO::personRisks)
                .flatMap(Collection::stream)
                .map(RiskDTO::premium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
