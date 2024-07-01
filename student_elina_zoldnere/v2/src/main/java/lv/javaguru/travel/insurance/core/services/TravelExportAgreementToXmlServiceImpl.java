package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelExportAgreementToXmlCoreResult;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.services.writers.ExportedAgreementRegistrar;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.serialize.AgreementSerialDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
import java.util.List;

@Component
@Transactional
class TravelExportAgreementToXmlServiceImpl implements TravelExportAgreementToXmlService {

    private static final Logger logger = LoggerFactory.getLogger(TravelExportAgreementToXmlServiceImpl.class);

    @Value("${agreement.xml.exporter.job.path}")
    private String exportPath;

    @Autowired
    private TravelGetAgreementService getAgreementService;
    @Autowired
    private ExportedAgreementRegistrar registrar;
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public TravelExportAgreementToXmlCoreResult exportAgreement(TravelExportAgreementToXmlCoreCommand command) {
        String uuid = command.getUuid();
        Placeholder uuidPlaceH = new Placeholder("UUID", uuid);

        logger.info("Agreement uuid {} export job started", uuid);

        try {
            return processExport(uuid, uuidPlaceH);

        } catch (JAXBException e) {
            return handleException("ERROR_CODE_81", uuid, uuidPlaceH, e);
        } catch (IOException e) {
            return handleException("ERROR_CODE_82", uuid, uuidPlaceH, e);
        }
    }

    private TravelExportAgreementToXmlCoreResult processExport(String uuid, Placeholder uuidPlaceH)
            throws JAXBException, IOException {

        AgreementSerialDTO agreement = getAgreementData(uuid);
        String xmlString = convertAgreementToXml(agreement);
        boolean success = writeAgreementXmlToFileIfNotExists(uuid, xmlString);

        if (success) {
            logger.info("Agreement uuid {} export job completed", uuid);
            return new TravelExportAgreementToXmlCoreResult();
        } else {
            logger.warn("File already exists for agreement with UUID {}", uuid);
            return new TravelExportAgreementToXmlCoreResult(List.of(
                    errorFactory.buildError("ERROR_CODE_83", List.of(uuidPlaceH))
            ));
        }
    }

    private TravelExportAgreementToXmlCoreResult handleException(
            String errorCode, String uuid, Placeholder uuidPlaceH, Exception e) {
        logger.warn("Exception during export for agreement with UUID {}", uuid, e);
        return new TravelExportAgreementToXmlCoreResult(List.of(
                errorFactory.buildError(errorCode, List.of(uuidPlaceH))
        ));
    }

    private AgreementSerialDTO getAgreementData(String uuid) {
        TravelGetAgreementCoreCommand command = new TravelGetAgreementCoreCommand(uuid);
        TravelGetAgreementCoreResult agreementResult = getAgreementService.getAgreement(command);
        return agreementResult.getAgreement();
    }

    private String convertAgreementToXml(AgreementSerialDTO agreement) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(AgreementSerialDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(agreement, sw);
        return sw.toString();
    }

    private boolean writeAgreementXmlToFileIfNotExists(String uuid, String xmlString) throws IOException {
        String filePath = exportPath + "/agreement_" + uuid + ".xml";

        if (Files.exists(Paths.get(filePath))) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { //try-with-resources
            writer.write(xmlString);
            registrar.registerExport(uuid, new Date());
            return true;
        }
    }

}
