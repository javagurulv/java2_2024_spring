package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreResult;

public interface TravelExportAgreementToXmlService {

    TravelExportAgreementToXmlCoreResult export(TravelExportAgreementToXmlCoreCommand command);

}
