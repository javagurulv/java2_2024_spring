package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelAgreementValidator agreementValidator;
    @Autowired
    private CalculateAndUpdateAgreementWithPremiums calculateAndUpdateAgreement;
    @Autowired
    private PersonWriter writer;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        return errors.isEmpty()
                ? buildPremiumResponse(command.getAgreement())
                : buildErrorResponse(errors);
    }

    private TravelCalculatePremiumCoreResult buildPremiumResponse(AgreementDTO agreement) {
        AgreementDTO agreementWithPremiums = calculateAndUpdateAgreement.calculateAgreementPremiums(agreement);
        writePersons(agreement);
        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreementWithPremiums);
        return coreResult;
    }

    private TravelCalculatePremiumCoreResult buildErrorResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

    private void writePersons(AgreementDTO agreement) {
        agreement.persons().forEach(person -> writer.writePersonIfNotExists(person));
    }

}
