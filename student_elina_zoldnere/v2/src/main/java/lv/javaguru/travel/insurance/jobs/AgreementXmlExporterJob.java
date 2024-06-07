package lv.javaguru.travel.insurance.jobs;

import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetNotExportedAgreementUuidsCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.services.TravelGetAgreementService;
import lv.javaguru.travel.insurance.core.services.TravelGetNotExportedAgreementUuidsService;
import lv.javaguru.travel.insurance.dto.serialize.AgreementSerialDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class AgreementXmlExporterJob {

    @Value("${agreement.xml.exporter.job.enabled:false}")
    private boolean jobEnabled;
    @Value("${agreement.xml.exporter.job.path}")
    private String exportPath;

    @Autowired
    private TravelGetNotExportedAgreementUuidsService getUuidsService;
    @Autowired
    private TravelGetAgreementService getAgreementService;
    @Autowired
    private ExportedAgreementRegistrar writer;

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporterJob.class);

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void executeJob() {
        if (jobEnabled) {
            logger.info("AgreementXmlExporterJob started");
            processAgreements();
            logger.info("AgreementXmlExporterJob finished");
        }
    }

    private void processAgreements() {
        TravelGetNotExportedAgreementUuidsCoreResult uuidResult =
                getUuidsService.getUuids(new TravelGetNotExportedAgreementUuidsCoreCommand());
        uuidResult.getUuids().forEach(this::processAgreement);
    }

    private void processAgreement(String uuid) {
        TravelGetAgreementCoreCommand command = new TravelGetAgreementCoreCommand(uuid);
        TravelGetAgreementCoreResult agreementResult = getAgreementService.getAgreement(command);
        AgreementSerialDTO agreement = agreementResult.getAgreement();
        logger.info("Agreement uuid {} export job started", uuid);

        try {
            String xmlString = convertAgreementToXml(agreement);
            Optional<Date> exportedAt = writeAgreementXmlToFile(uuid, xmlString);

            if (exportedAt.isPresent()) {
                writer.registerExport(uuid, exportedAt.get());
                logger.info("Agreement uuid {} export job completed", uuid);
            } else {
                logger.warn("File already exists for agreement with UUID {}", uuid);
            }

        } catch (JAXBException e) {
            logger.warn("Could not convert to xml agreement with UUID {}", uuid, e);
        } catch (IOException e) {
            logger.warn("Could not write to file agreement with UUID {}", uuid, e);
    }
}

    private String convertAgreementToXml(AgreementSerialDTO agreement) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(AgreementSerialDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(agreement, sw);
        return sw.toString();
    }

    private Optional<Date> writeAgreementXmlToFile(String uuid, String xmlString) throws IOException {
        String filePath = exportPath + "/agreement_" + uuid + ".xml";

        if (Files.exists(Paths.get(filePath))) {
            return Optional.empty();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { //try-with-resources
            writer.write(xmlString);
            return Optional.of(new Date());
        }
    }

}
