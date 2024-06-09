package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.services.writers.entity.EntityWriter;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelAgreementValidator agreementValidator;
    @Autowired
    private CalculateAndUpdateAgreementWithPremiums calculateAndUpdateAgreement;
    @Autowired
    private EntityWriter entityWriter;


    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        return errors.isEmpty()
                ? buildPremiumResponse(command.getAgreement())
                : buildErrorResponse(errors);
    }

    private TravelCalculatePremiumCoreResult buildPremiumResponse(AgreementDTO agreement) {
        AgreementDTO agreementWithPremiums = calculateAndUpdateAgreement.calculateAgreementPremiums(agreement);
        entityWriter.writeEntities(agreementWithPremiums);
        TravelCalculatePremiumCoreResult coreResult = new TravelCalculatePremiumCoreResult();
        coreResult.setAgreement(agreementWithPremiums);
        return coreResult;
    }

    private TravelCalculatePremiumCoreResult buildErrorResponse(List<ValidationErrorDTO> errors) {
        return new TravelCalculatePremiumCoreResult(errors);
    }

}
