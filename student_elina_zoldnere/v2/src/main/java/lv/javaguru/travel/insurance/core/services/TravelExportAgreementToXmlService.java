package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreResult;

public interface TravelExportAgreementToXmlService {
    TravelExportAgreementToXmlCoreResult exportAgreement(TravelExportAgreementToXmlCoreCommand command);

}
