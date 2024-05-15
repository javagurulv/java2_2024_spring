package lv.javaguru.travel.insurance.core.services.writers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementPersonEntityRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AgreementPersonWriter {

    @Autowired
    private AgreementPersonEntityRepository repository;
    @Autowired
    private PersonWriter personWriter;
    @Autowired
    private AgreementPersonRisksWriter personRisksWriter;

    AgreementPersonEntity writeAgreementPerson(PersonDTO person, AgreementEntity agreementEntity) {
        AgreementPersonEntity agreementPersonEntity = new AgreementPersonEntity();
        agreementPersonEntity.setAgreementEntity(agreementEntity);
        agreementPersonEntity.setPersonEntity(personWriter.writePersonIfNotExists(person));
        agreementPersonEntity.setMedicalRiskLimitLevel(person.medicalRiskLimitLevel());
        return repository.save(agreementPersonEntity);
    }

}

