package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;


import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired
    private DayCount dayCount;
    @Autowired
    private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Autowired
    private AgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired
    private MedicalRiskLimitLevelCalculator medicalRiskLimitLevelCalculator;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        var daysCount = dayCount.calculateDayCount(request);
        var countryDefaultRate = countryDefaultDayRateCalculator.findCountryDefaultDayRate(request);
        var ageCoefficient = ageCoefficientCalculator.findAgeCoefficient(request);
        var medicalRiskLimitLevel = medicalRiskLimitLevelCalculator.calculateRiskLimitLevel(request);
        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .multiply(medicalRiskLimitLevel)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
