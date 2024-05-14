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

    @Override
    public void writeEntities(AgreementDTO agreement) {
        writePersons(agreement);
        AgreementEntity agreementEntity = agreementWriter.writeAgreement(agreement);
        risksWriter.writeSelectedRisks(agreement, agreementEntity);
    }
    private void writePersons(AgreementDTO agreement) {
        agreement.persons().forEach(person -> personWriter.writePersonIfNotExists(person));
    }

}