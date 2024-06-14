package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelGetNotExportedAgreementUuidsServiceImpl implements TravelGetNotExportedAgreementUuidsService {

    @Autowired
    private AgreementEntityRepository agreementRepository;

    public TravelGetNotExportedAgreementUuidsCoreResult getUuids(TravelGetNotExportedAgreementUuidsCoreCommand command) {
        List<String> notExportedUuids = agreementRepository.findNotExportedUuids();
        return new TravelGetNotExportedAgreementUuidsCoreResult(null, notExportedUuids);
    }

}
