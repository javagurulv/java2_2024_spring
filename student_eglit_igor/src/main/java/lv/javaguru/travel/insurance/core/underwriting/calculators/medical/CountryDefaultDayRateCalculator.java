package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
class CountryDefaultDayRateCalculator {

    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    BigDecimal findCountryDefaultDayRate(TravelCalculatePremiumRequest request) {
        return countryDefaultDayRateRepository.findByCountryIc(request.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + request.getCountry()));
    }
}
