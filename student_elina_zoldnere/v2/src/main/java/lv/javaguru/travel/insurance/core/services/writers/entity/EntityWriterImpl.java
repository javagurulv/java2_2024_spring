package lv.javaguru.travel.insurance.core.services.writers.entity;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class EntityWriterImpl implements EntityWriter {

    @Autowired
    private AgreementWriter agreementWriter;
    @Autowired
    private SelectedRisksWriter risksWriter;
    @Autowired
    private AgreementPersonWriter agreementPersonWriter;
    @Autowired
    private AgreementPersonRisksWriter agreementPersonRisksWriter;

    @Override
    public void writeEntities(AgreementDTO agreement) {
        AgreementEntity agreementEntity = agreementWriter.writeAgreement(agreement);
        agreement.persons()
                .forEach(person -> {
                    AgreementPersonEntity agreementPersonEntity =
                            agreementPersonWriter.writeAgreementPerson(person, agreementEntity);
                    agreementPersonRisksWriter.writeAgreementPersonRisks(person, agreementPersonEntity);
                });
        risksWriter.writeSelectedRisks(agreement, agreementEntity);
    }

}