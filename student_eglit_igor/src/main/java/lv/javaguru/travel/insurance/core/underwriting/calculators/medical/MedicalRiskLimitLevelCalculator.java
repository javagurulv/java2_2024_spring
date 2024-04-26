package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
class MedicalRiskLimitLevelCalculator {

    @Autowired
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    public BigDecimal calculateRiskLimitLevel(TravelCalculatePremiumRequest request) {
        return medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc(
                request.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Medical risk limit level not found by name " + request.getMedicalRiskLimitLevel()));

    }
}
