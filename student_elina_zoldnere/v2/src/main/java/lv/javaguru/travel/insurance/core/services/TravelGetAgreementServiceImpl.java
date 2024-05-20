package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementUuidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TravelGetAgreementServiceImpl implements TravelGetAgreementService {

    @Autowired
    private TravelAgreementUuidValidator uuidValidator;
    @Autowired
    private AgreementEntityRepository agreementEntityRepository;
    @Autowired
    private EntityReader reader;

    @Override
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command) {
        List<ValidationErrorDTO> errors = uuidValidator.validate(command.getUuid());
        return errors.isEmpty()
                ? buildAgreementResponse(command.getUuid())
                : buildErrorResponse(errors);
    }

    private TravelGetAgreementCoreResult buildAgreementResponse(String uuid) {
        AgreementDTO agreement = reader.readEntitiesByUuid(uuid); // implementation here
        TravelGetAgreementCoreResult result = new TravelGetAgreementCoreResult();
        result.setAgreement(agreement);
        return result;
    }

    private TravelGetAgreementCoreResult buildErrorResponse(List<ValidationErrorDTO> errors) {
        return new TravelGetAgreementCoreResult(errors);
    }

}