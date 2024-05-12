package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired private DateTimeService dateTimeUtil;
    @Autowired private DayCountCalculator dayCountCalculator;
    @Autowired private AgeCoefficientRepository ageCoefficientRepository;
    @Autowired private AgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysCount = dayCountCalculator.calculate(request);
        var countryDefaultRate = countryDefaultDayRateCalculator.calculate(request);
        var ageCoefficient = ageCoefficientCalculator.calculate(request);
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
