package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.medical.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.medical.MedicalRiskLimitLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class MedicalRiskLimitLevelCoefficientRetriever {

    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;

    @Autowired
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    BigDecimal setLimitLevelCoefficient(PersonDTO person) {
        return medicalRiskLimitLevelEnabled
                ? findLimitLevelCoefficient(person)
                : setDefaultValue();
    }

    private BigDecimal findLimitLevelCoefficient(PersonDTO person) {
        String medicalRiskLimitLevelIc = person.medicalRiskLimitLevel();
        return medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc(medicalRiskLimitLevelIc)
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException
                        ("Medical risk limit level = " + medicalRiskLimitLevelIc + " coefficient not found!"));
    }

    private BigDecimal setDefaultValue() {
        return BigDecimal.ONE;
    }

}
