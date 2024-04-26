package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class MedicalRiskLimitLevelCoefficientRetriever {

    @Autowired
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    public BigDecimal findLimitLevelCoefficient(TravelCalculatePremiumRequest request) {
        String medicalRiskLimitLevelIc = request.getMedicalRiskLimitLevel();
        return medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc(medicalRiskLimitLevelIc)
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException
                        ("Medical risk limit level = " + medicalRiskLimitLevelIc + " coefficient not found!"));
    }

}
