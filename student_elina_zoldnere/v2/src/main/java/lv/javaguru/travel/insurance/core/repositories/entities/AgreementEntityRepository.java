package lv.javaguru.travel.insurance.core.repositories.entities;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AgreementEntityRepository extends JpaRepository<AgreementEntity, Long> {

    Optional<AgreementEntity> findByUuid(String uuid);

    @Query("SELECT a.uuid FROM AgreementEntity a WHERE a.uuid NOT IN " +
            "(SELECT e.uuid FROM ExportedAgreementUuidEntity e)")
    List<String> findNotExportedUuids();


}