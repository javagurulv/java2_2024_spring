package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class CountryDefaultDayRateRetriever {

    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    public BigDecimal findCountryDefaultDayRate(TravelCalculatePremiumRequest request) {
        String country_ic = request.getCountry();
        return countryDefaultDayRateRepository.findByCountryIc(country_ic)
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country ic = " + country_ic + " default day rate not found!"));
    }

}
