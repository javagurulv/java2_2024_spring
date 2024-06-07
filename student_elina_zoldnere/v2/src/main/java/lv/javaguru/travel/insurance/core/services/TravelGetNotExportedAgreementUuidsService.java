package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;

public interface TravelGetNotExportedAgreementUuidsService {

    TravelGetNotExportedAgreementUuidsCoreResult getUuids(TravelGetNotExportedAgreementUuidsCoreCommand command);

}


