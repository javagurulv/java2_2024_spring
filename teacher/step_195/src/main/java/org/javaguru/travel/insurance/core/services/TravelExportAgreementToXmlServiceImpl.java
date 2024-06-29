package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreResult;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.domain.entities.AgreementXmlExportEntity;
import org.javaguru.travel.insurance.core.repositories.entities.AgreementXmlExportEntityRepository;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.List;

@Component
@Transactional
class TravelExportAgreementToXmlServiceImpl
    implements TravelExportAgreementToXmlService {

    private static final Logger logger = LoggerFactory.getLogger(TravelExportAgreementToXmlServiceImpl.class);

    @Value( "${agreement.xml.exporter.job.path}" )
    private String agreementExportPath;

    @Autowired private TravelGetAgreementService agreementService;
    @Autowired private AgreementXmlExportEntityRepository agreementXmlExportEntityRepository;
    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public TravelExportAgreementToXmlCoreResult export(TravelExportAgreementToXmlCoreCommand command) {
        try {
            AgreementDTO agreement = getAgreementData(command.getUuid());
            String agreementXml = convertAgreementToXml(agreement);
            storeXmlToFile(command.getUuid(), agreementXml);
        } catch (Exception e) {
            logger.info("AgreementXmlExporterJob failed for agreement uuid = " + command.getUuid(), e);
            return new TravelExportAgreementToXmlCoreResult(List.of(
                    errorFactory.buildError("ERROR_CODE_20")));
        }

        saveToDatabaseInfoAboutExportedAgreement(command);

        return new TravelExportAgreementToXmlCoreResult();
    }

    private void saveToDatabaseInfoAboutExportedAgreement(TravelExportAgreementToXmlCoreCommand command) {
        AgreementXmlExportEntity agreementXmlExportEntity = new AgreementXmlExportEntity();
        agreementXmlExportEntity.setAgreementUuid(command.getUuid());
        agreementXmlExportEntity.setAlreadyExported(Boolean.TRUE);
        agreementXmlExportEntityRepository.save(agreementXmlExportEntity);
    }

    private AgreementDTO getAgreementData(String agreementUuid) {
        TravelGetAgreementCoreCommand command = new TravelGetAgreementCoreCommand(agreementUuid);
        return agreementService.getAgreement(command).getAgreement();
    }

    private String convertAgreementToXml(AgreementDTO agreement) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(AgreementDTO.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(agreement, sw);
        return sw.toString();
    }

    private void storeXmlToFile(String agreementUuid,
                                String agreementXml) throws IOException {
        File file = new File(agreementExportPath + "/agreement-" + agreementUuid + ".xml");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(agreementXml);
        bw.close();
    }

}
