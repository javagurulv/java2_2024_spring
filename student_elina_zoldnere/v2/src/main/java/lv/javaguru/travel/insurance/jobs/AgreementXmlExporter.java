package lv.javaguru.travel.insurance.jobs;

import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.services.TravelGetAgreementService;
import lv.javaguru.travel.insurance.dto.serialize.AgreementSerialDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@Component
class AgreementXmlExporter {

    @Value("${agreement.xml.exporter.job.path}")
    private String exportPath;

    @Autowired
    private TravelGetAgreementService getAgreementService;
    @Autowired
    private ExportedAgreementRegistrar writer;

    private static final Logger logger = LoggerFactory.getLogger(AgreementXmlExporter.class);

    void processAgreement(String uuid) {
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
