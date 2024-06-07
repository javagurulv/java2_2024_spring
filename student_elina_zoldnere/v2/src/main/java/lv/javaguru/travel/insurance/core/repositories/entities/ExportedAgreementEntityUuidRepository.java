package lv.javaguru.travel.insurance.core.repositories.entities;

import lv.javaguru.travel.insurance.core.domain.entities.ExportedAgreementUuidEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Repository for accessing exported agreement UUIDs and timestamps.
Currently not used directly but exists for potential future use
and to clarify the presence of the exported_agreements table.
 */
public interface ExportedAgreementEntityUuidRepository extends JpaRepository<ExportedAgreementUuidEntity, Long> {

}
