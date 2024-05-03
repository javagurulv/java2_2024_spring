package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class AgeCoefficientRetriever {

    @Value("${age.coefficient.enabled:false}")
    private Boolean ageCoefficientEnabled;

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    BigDecimal setAgeCoefficient(TravelCalculatePremiumRequestV1 request) {
        return ageCoefficientEnabled
                ? findAgeCoefficient(request)
                : setDefaultValue();
    }

    private BigDecimal findAgeCoefficient(TravelCalculatePremiumRequestV1 request) {
        Date personBirthDate = request.getPersonBirthDate();
        Date currentDate = dateTimeUtil.currentTimeToday(); // hours and seconds does not matter
        Integer age = dateTimeUtil.calculateDifferenceBetweenDatesInYears(personBirthDate, currentDate);

        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Coefficient for age = " + age + " not found!"));
    }

    private BigDecimal setDefaultValue() {
        return BigDecimal.ONE;
    }

}
