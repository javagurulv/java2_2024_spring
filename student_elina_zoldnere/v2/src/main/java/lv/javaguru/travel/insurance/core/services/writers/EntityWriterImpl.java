package lv.javaguru.travel.insurance.core.services.writers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.services.EntityWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class EntityWriterImpl implements EntityWriter {

    @Autowired
    private AgreementWriter agreementWriter;
    @Autowired
    private PersonWriter personWriter;
    @Autowired
    private SelectedRisksWriter risksWriter;
    @Autowired
    private AgreementPersonsWriter agreementPersonsWriter;

    @Override
    public void writeEntities(AgreementDTO agreement) {
        AgreementEntity agreementEntity = agreementWriter.writeAgreement(agreement);
        agreementPersonsWriter.writeAgreementPersons(agreement, agreementEntity);
        risksWriter.writeSelectedRisks(agreement, agreementEntity);
    }

}