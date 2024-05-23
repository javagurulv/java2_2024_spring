package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.cancellation.TCAgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.cancellation.TCAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class TCAgeCoefficientRetriever {

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private TCAgeCoefficientRepository repository;

    BigDecimal findAgeCoefficient(PersonDTO person) {
        Date personBirthDate = person.personBirthDate();
        Date currentDate = dateTimeUtil.currentTimeToday(); // hours and seconds does not matter
        Integer age = dateTimeUtil.calculateDifferenceBetweenDatesInYears(personBirthDate, currentDate);

        return repository.findCoefficientByAge(age)
                .map(TCAgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Coefficient for age = " + age + " not found!"));
    }

}
