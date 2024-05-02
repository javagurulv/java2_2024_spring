package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
class AgeCoefficientCalculator {
    @Value("${medical.risk.age.coefficient.enabled:true}")
    private Boolean medicalRiskAgeCoefficientEnabled;

    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;


    BigDecimal findAgeCoefficient(TravelCalculatePremiumRequestV1 request) {
        return medicalRiskAgeCoefficientEnabled
                ? calculate(request)
                : getDefaultValue();
    }

    private BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }

    BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        return ageCoefficientRepository.findAgeCoefficientByAge(calculateAge(request))
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + calculateAge(request)));
    }

    Integer calculateAge(TravelCalculatePremiumRequestV1 request) {
        Period period = Period.between(request.getPersonBirthDate(), request.getAgreementDateFrom());
        return period.getYears();
    }
}
