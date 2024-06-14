package lv.javaguru.travel.insurance.core.services.writers.entity;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.AgreementEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class AgreementWriter {

    @Autowired
    private AgreementEntityRepository repository;

    AgreementEntity writeAgreement(AgreementDTO agreement) {
        AgreementEntity newAgreementEntity = new AgreementEntity();
        newAgreementEntity.setDateFrom(agreement.agreementDateFrom());
        newAgreementEntity.setDateTo(agreement.agreementDateTo());
        newAgreementEntity.setCountry(agreement.country());
        newAgreementEntity.setPremium(agreement.agreementPremium());
        newAgreementEntity.setUuid(agreement.uuid());
        return repository.save(newAgreementEntity);
    }

}

