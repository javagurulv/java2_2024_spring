package lv.javaguru.travel.insurance.core.sercices;

import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementUuidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
class TravelGetAgreementServiceImpl implements TravelGetAgreementService{

    @Autowired
    private TravelAgreementUuidValidator agreementUuidValidator;
    @Autowired private AgreementDTOLoader agreementDTOLoader;

    @Override
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command){
        List<ValidationErrorDTO> errors = agreementUuidValidator.validate(command.getUuid());
        return errors.isEmpty()
                ? buildResponse(command.getUuid())
                : buildResponse(errors);
    }

    private TravelGetAgreementCoreResult buildResponse(String agreementUuid) {
        TravelGetAgreementCoreResult coreResult = new TravelGetAgreementCoreResult();
        coreResult.setAgreement(agreementDTOLoader.load(agreementUuid));
        return coreResult;
    }

    private TravelGetAgreementCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelGetAgreementCoreResult(errors);
    }

}
