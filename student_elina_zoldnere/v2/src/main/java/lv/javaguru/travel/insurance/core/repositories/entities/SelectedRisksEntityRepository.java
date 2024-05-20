package lv.javaguru.travel.insurance.core.repositories.entities;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.SelectedRisksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectedRisksEntityRepository extends JpaRepository<SelectedRisksEntity, Long> {

    List<SelectedRisksEntity> findByAgreementEntity(AgreementEntity agreement);

}
