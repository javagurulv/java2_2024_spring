package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;


import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;

@Component
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired
    private DayCount dayCount;
    @Autowired
    private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Autowired
    private AgeCoefficientCalculator ageCoefficientCalculator;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysCount = dayCount.calculateDayCount(request);
        var countryDefaultRate = countryDefaultDayRateCalculator.findCountryDefaultDayRate(request);
        var ageCoefficient = ageCoefficientCalculator.findAgeCoefficient(request);
        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
