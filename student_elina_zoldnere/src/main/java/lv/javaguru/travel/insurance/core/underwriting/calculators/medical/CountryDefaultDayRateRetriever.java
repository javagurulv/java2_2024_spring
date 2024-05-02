package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CountryDefaultDayRateRetriever {

    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    BigDecimal findCountryDefaultDayRate(TravelCalculatePremiumRequestV1 request) {
        String country_ic = request.getCountry();
        return countryDefaultDayRateRepository.findByCountryIc(country_ic)
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country ic = " + country_ic + " default day rate not found!"));
    }

}
