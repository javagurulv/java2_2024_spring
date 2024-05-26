package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.util.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.repositories.AgeCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
class AgeCoefficientCalculator {

    @Value( "${medical.risk.age.coefficient.enabled:false}" )
    private Boolean medicalRiskAgeCoefficientEnabled;

    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;
    @Autowired private DateTimeService dateTimeUtil;

    BigDecimal calculate(TravelCalculatePremiumRequest request) {
        return medicalRiskAgeCoefficientEnabled
                ? getCoefficient(request)
                : getDefaultValue();
    }

    private BigDecimal getCoefficient(TravelCalculatePremiumRequest request) {
        int age = calculateAge(request);
        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + age));
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        LocalDate personBirthDate = toLocalDate(request.getPersonBirthDate());
        LocalDate currentDate = toLocalDate(dateTimeUtil.getCurrentDateTime());
        return Period.between(personBirthDate, currentDate).getYears();
    }

    private static BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
