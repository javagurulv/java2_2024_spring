package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.medical.TMAgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.medical.TMAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class TMAgeCoefficientRetriever {

    @Value("${age.coefficient.enabled:false}")
    private Boolean ageCoefficientEnabled;

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private TMAgeCoefficientRepository ageCoefficientRepository;

    BigDecimal setAgeCoefficient(PersonDTO person) {
        return ageCoefficientEnabled
                ? findAgeCoefficient(person)
                : setDefaultValue();
    }

    private BigDecimal findAgeCoefficient(PersonDTO person) {
        Date personBirthDate = person.personBirthDate();
        Date currentDate = dateTimeUtil.currentTimeToday(); // hours and seconds does not matter
        Integer age = dateTimeUtil.calculateDifferenceBetweenDatesInYears(personBirthDate, currentDate);

        return ageCoefficientRepository.findCoefficient(age)
                .map(TMAgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Coefficient for age = " + age + " not found!"));
    }

    private BigDecimal setDefaultValue() {
        return BigDecimal.ONE;
    }

}
