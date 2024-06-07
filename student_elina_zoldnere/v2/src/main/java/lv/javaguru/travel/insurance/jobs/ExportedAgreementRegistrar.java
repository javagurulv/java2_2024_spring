package lv.javaguru.travel.insurance.jobs;

import lv.javaguru.travel.insurance.core.domain.entities.ExportedAgreementUuidEntity;
import lv.javaguru.travel.insurance.core.repositories.entities.ExportedAgreementEntityUuidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
class ExportedAgreementRegistrar {

    @Autowired
    private ExportedAgreementEntityUuidRepository repository;

    void registerExport(String uuid, Date exportedAt) {
        ExportedAgreementUuidEntity entity = new ExportedAgreementUuidEntity();
        entity.setUuid(uuid);
        entity.setExportedAt(exportedAt);
        repository.save(entity);
    }

}
