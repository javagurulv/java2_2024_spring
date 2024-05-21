package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.medical.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.medical.CountryDefaultDayRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CountryDefaultDayRateRetriever {

    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    BigDecimal findCountryDefaultDayRate(AgreementDTO agreement) {
        String country_ic = agreement.country();
        return countryDefaultDayRateRepository.findByCountryIc(country_ic)
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country ic = " + country_ic + " default day rate not found!"));
    }

}
