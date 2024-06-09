package lv.javaguru.travel.insurance.core.services.writers.entity;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonRisksEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementPersonRisksEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AgreementPersonRisksWriter {

    @Autowired
    private AgreementPersonRisksEntityRepository repository;

    void writeAgreementPersonRisks(PersonDTO person, AgreementPersonEntity agreementPersonEntity) {
        person.personRisks().forEach(risk -> {
            AgreementPersonRisksEntity agreementPersonRisksEntity = new AgreementPersonRisksEntity();
            agreementPersonRisksEntity.setAgreementPersonEntity(agreementPersonEntity);
            agreementPersonRisksEntity.setRiskIc(risk.riskIc());
            agreementPersonRisksEntity.setPremium(risk.premium());
            repository.save(agreementPersonRisksEntity);
        });
    }

}