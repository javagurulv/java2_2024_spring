package org.javaguru.travel.insurance.core.repositories.entities;

import org.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.javaguru.travel.insurance.core.domain.entities.SelectedRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectedRiskEntityRepository extends JpaRepository<SelectedRiskEntity, Long> {

    List<SelectedRiskEntity> findByAgreement(AgreementEntity agreement);

}
