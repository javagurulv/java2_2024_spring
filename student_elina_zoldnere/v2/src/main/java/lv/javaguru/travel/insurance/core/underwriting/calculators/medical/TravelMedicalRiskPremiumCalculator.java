package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
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
    private TMAgeCoefficientRetriever ageCoefficientRetriever;
    @Autowired
    private MedicalRiskLimitLevelCoefficientRetriever limitLevelCoefficientRetriever;

    @Override
    public BigDecimal calculateRiskPremium(AgreementDTO agreement, PersonDTO person) {
        BigDecimal dayCount = dayCountCalculator.calculateDayCount(agreement);
        BigDecimal countryDefaultDayRate = countryDefaultDayRateRetriever.findCountryDefaultDayRate(agreement);
        BigDecimal ageCoefficient = ageCoefficientRetriever.setAgeCoefficient(person);
        BigDecimal limitLevelCoefficient = limitLevelCoefficientRetriever.setLimitLevelCoefficient(person);
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
