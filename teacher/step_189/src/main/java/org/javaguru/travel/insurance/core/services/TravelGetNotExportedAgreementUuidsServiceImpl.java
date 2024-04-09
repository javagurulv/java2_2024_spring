package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
class TravelGetNotExportedAgreementUuidsServiceImpl
        implements TravelGetNotExportedAgreementUuidsService {

    @Autowired
    private AgreementEntityRepository agreementRepository;

    @Override
    public TravelGetNotExportedAgreementUuidsCoreResult getAgreementUuids(TravelGetNotExportedAgreementUuidsCoreCommand command) {
        List<String> agreementUuids = agreementRepository.getNotExportedAgreementUuids();
        return new TravelGetNotExportedAgreementUuidsCoreResult(null, agreementUuids);
    }

}
