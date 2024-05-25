package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl
        implements TravelCalculatePremiumService {

    @Autowired
    private TravelAgreementValidator agreementValidator;
    @Autowired
    private AgreementTotalPremiumCalculator agreementTotalPremiumCalculator;
    @Autowired
    private AgreementPersonsPremiumCalculator agreementPersonsPremiumCalculator;
    @Autowired
    private ResponseBuilder responseBuilder;
    @Autowired
    private PersonSaver personSaver;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        if (errors.isEmpty()) {
            calculatePremium(command.getAgreement());
            savePersons(command.getAgreement());
            return responseBuilder.buildResponse(command.getAgreement());
        } else {
            return responseBuilder.buildResponse(errors);
        }
    }
    private void calculatePremium(AgreementDTO agreement) {
        agreementPersonsPremiumCalculator.calculateRiskPremiums(agreement);
        agreement.setAgreementPremium(agreementTotalPremiumCalculator.calculate(agreement));
    }
    private void savePersons(AgreementDTO agreement) {
        agreement.getPersons().forEach(person ->personSaver.savePerson(person));
    }
}
