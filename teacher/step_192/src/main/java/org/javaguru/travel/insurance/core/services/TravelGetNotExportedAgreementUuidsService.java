package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;

public interface TravelGetNotExportedAgreementUuidsService {

    TravelGetNotExportedAgreementUuidsCoreResult getAgreementUuids(TravelGetNotExportedAgreementUuidsCoreCommand command);

}
