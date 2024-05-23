package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.cancellation.TCCountrySafetyRatingCoefficient;
import lv.javaguru.travel.insurance.core.repositories.cancellation.TCCountrySafetyRatingCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TCCountrySafetyRatingCoefficientRetriever {

    @Autowired
    private TCCountrySafetyRatingCoefficientRepository repository;

    BigDecimal findCountrySafetyRatingCoefficient(AgreementDTO agreement) {
        String country_ic = agreement.country();
        return repository.findCoefficientByCountryIc(country_ic)
                .map(TCCountrySafetyRatingCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Coefficient for country = " + country_ic + " not found!"));
    }

}
