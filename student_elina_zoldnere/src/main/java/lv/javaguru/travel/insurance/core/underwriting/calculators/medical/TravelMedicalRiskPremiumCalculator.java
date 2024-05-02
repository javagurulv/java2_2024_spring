package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired
    private DayCountCalculator dayCountCalculator;
    @Autowired
    private CountryDefaultDayRateRetriever countryDefaultDayRateRetriever;
    @Autowired
    private AgeCoefficientRetriever ageCoefficientRetriever;
    @Autowired
    private MedicalRiskLimitLevelCoefficientRetriever limitLevelCoefficientRetriever;

    @Override
    public BigDecimal calculateRiskPremium(TravelCalculatePremiumRequestV1 request) {
        BigDecimal dayCount = dayCountCalculator.calculateDayCount(request);
        BigDecimal countryDefaultDayRate = countryDefaultDayRateRetriever.findCountryDefaultDayRate(request);
        BigDecimal ageCoefficient = ageCoefficientRetriever.setAgeCoefficient(request);
        BigDecimal limitLevelCoefficient = limitLevelCoefficientRetriever.setLimitLevelCoefficient(request);
        return countryDefaultDayRate
                .multiply(dayCount)
                .multiply(ageCoefficient)
                .multiply(limitLevelCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
