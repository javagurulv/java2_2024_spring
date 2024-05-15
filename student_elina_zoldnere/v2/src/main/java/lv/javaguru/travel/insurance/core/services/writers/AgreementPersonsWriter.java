package lv.javaguru.travel.insurance.core.services.writers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementPersonEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AgreementPersonsWriter {

    @Autowired
    private AgreementPersonEntityRepository repository;
    @Autowired
    private PersonWriter personWriter;

    void writeAgreementPersons(AgreementDTO agreement, AgreementEntity agreementEntity) {
        agreement.persons().stream()
                .map(person -> {
                    AgreementPersonEntity agreementPersonEntity = new AgreementPersonEntity();
                    agreementPersonEntity.setAgreementEntity(agreementEntity);
                    agreementPersonEntity.setPersonEntity(personWriter.writePersonIfNotExists(person));
                    agreementPersonEntity.setMedicalRiskLimitLevel(person.medicalRiskLimitLevel());
                    return agreementPersonEntity;
                })
                .forEach(repository::save);
    }

}

