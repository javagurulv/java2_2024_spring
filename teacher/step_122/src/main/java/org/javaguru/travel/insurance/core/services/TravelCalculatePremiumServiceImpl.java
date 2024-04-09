package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired private TravelAgreementValidator agreementValidator;
    @Autowired private TravelPremiumUnderwriting premiumUnderwriting;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        return errors.isEmpty()
                ? buildResponse(command.getAgreement())
                : buildResponse(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private TravelCalculatePremiumCoreResult buildResponse(AgreementDTO agreement) {
        calculateRiskPremiumsForAllPersons(agreement);

        BigDecimal totalAgreementPremium = calculateTotalAgreementPremium(agreement);
        agreement.setAgreementPremium(totalAgreementPremium);

        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreement);
        return coreResult;
    }

    private void calculateRiskPremiumsForAllPersons(AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            TravelPremiumCalculationResult calculationResult = premiumUnderwriting.calculatePremium(agreement, person);
            person.setRisks(calculationResult.getRisks());
        });
    }

    private BigDecimal calculateTotalAgreementPremium(AgreementDTO agreement) {
        return agreement.getPersons().stream()
                .map(PersonDTO::getRisks)
                .flatMap(Collection::stream)
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
