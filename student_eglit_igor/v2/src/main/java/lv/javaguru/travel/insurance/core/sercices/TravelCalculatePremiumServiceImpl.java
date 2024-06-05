package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
class TravelCalculatePremiumServiceImpl
        implements TravelCalculatePremiumService {

    @Autowired
    private TravelAgreementValidator agreementValidator;
    @Autowired
    private AgreementTotalPremiumCalculator agreementTotalPremiumCalculator;
    @Autowired
    private AgreementPersonsPremiumCalculator agreementPersonsPremiumCalculator;
    @Autowired
    private PremiumServiceImplResponseBuilder premiumServiceImplResponseBuilder;
    @Autowired
    private AgreementEntityFactory agreementEntityFactory;

    @Override
    public TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementValidator.validate(command.getAgreement());
        if (errors.isEmpty()) {
            calculatePremium(command.getAgreement());
            AgreementEntity agreement = agreementEntityFactory.createAgreementEntity(command.getAgreement());
            command.getAgreement().setUuid(agreement.getUuid());
            return premiumServiceImplResponseBuilder.buildResponse(command.getAgreement());
        } else {
            return premiumServiceImplResponseBuilder.buildResponse(errors);
        }
    }
    private void calculatePremium(AgreementDTO agreement) {
        agreementPersonsPremiumCalculator.calculateRiskPremiums(agreement);
        agreement.setAgreementPremium(agreementTotalPremiumCalculator.calculate(agreement));
    }
}
