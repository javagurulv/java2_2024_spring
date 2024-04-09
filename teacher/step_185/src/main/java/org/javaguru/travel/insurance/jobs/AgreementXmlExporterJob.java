package org.javaguru.travel.insurance.jobs;

import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAllAgreementUuidsCoreCommand;
import org.javaguru.travel.insurance.core.api.command.TravelGetAllAgreementUuidsCoreResult;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.services.TravelGetAgreementService;
import org.javaguru.travel.insurance.core.services.TravelGetAllAgreementUuidsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class AgreementXmlExporterJob {

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Value( "${agreement.xml.exporter.job.enabled:false}" )
    private boolean jobEnabled;

    @Value( "${agreement.xml.exporter.job.path}" )
    private String agreementExportPath;

    @Autowired private TravelGetAllAgreementUuidsService allAgreementUuidsService;
    @Autowired private TravelGetAgreementService agreementService;


    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void doJob() {
        if (jobEnabled) {
            executeJob();
        }
    }

    private void executeJob() {
        logger.info("AgreementXmlExporterJob started");
        List<String> allAgreementUuids = getAllAgreementUuids();
        allAgreementUuids.forEach(this::exportAgreement);
        logger.info("AgreementXmlExporterJob finished");
    }

    private List<String> getAllAgreementUuids() {
        TravelGetAllAgreementUuidsCoreResult result = allAgreementUuidsService.getAgreement(
                new TravelGetAllAgreementUuidsCoreCommand()
        );
        return result.getAgreementUuids();
    }

    private void exportAgreement(String agreementUuid) {
        try {
            logger.info("AgreementXmlExporterJob started for uuid = " + agreementUuid);
            AgreementDTO agreement = getAgreementData(agreementUuid);
            String agreementXml = convertAgreementToXml(agreement);
            storeXmlToFile(agreementUuid, agreementXml);
            logger.info("AgreementXmlExporterJob finished for uuid = " + agreementUuid);
        } catch (Exception e) {
            logger.info("AgreementXmlExporterJob failed for agreement uuid = " + agreementUuid, e);
        }
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
