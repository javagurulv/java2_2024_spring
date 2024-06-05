package lv.javaguru.travel.insurance.core.repositories.medical;

import lv.javaguru.travel.insurance.core.domain.medical.MedicalRiskLimitLevel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRiskLimitLevelRepository extends JpaRepository<MedicalRiskLimitLevel, Long> {

    @Cacheable("medicalRiskLimitLevelCache")
    Optional<MedicalRiskLimitLevel> findByMedicalRiskLimitLevelIc(String medicalRiskLimitLevelIc);

}
