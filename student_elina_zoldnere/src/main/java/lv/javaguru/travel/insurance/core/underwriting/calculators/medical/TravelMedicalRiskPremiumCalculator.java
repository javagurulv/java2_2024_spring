package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired
    private DayCountCalculator dayCountCalculator;
    @Autowired
    private CountryDefaultDayRateRetriever countryDefaultDayRateRetriever;
    @Autowired
    private AgeCoefficientRetriever ageCoefficientRetriever;

    @Override
    public BigDecimal calculateRiskPremium(TravelCalculatePremiumRequest request) {
        BigDecimal dayCount = dayCountCalculator.calculateDayCount(request);
        BigDecimal countryDefaultDayRate = countryDefaultDayRateRetriever.findCountryDefaultDayRate(request);
        BigDecimal ageCoefficient = ageCoefficientRetriever.findAgeCoefficient(request);
        return countryDefaultDayRate
                .multiply(dayCount)
                .multiply(ageCoefficient);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
