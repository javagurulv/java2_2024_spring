package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired
    DateTimeUtil dateTimeUtil;
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysCount = calculateDayCount(request);
        var countryDefaultRate = findCountryDefaultDayRate(request);
        var ageCoefficient = findAgeCoefficient(request);
        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal findAgeCoefficient(TravelCalculatePremiumRequest request) {
       return ageCoefficientRepository.findAgeCoefficientByAge(calculateAge(request))
               .map(AgeCoefficient::getCoefficient)
               .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + calculateAge(request)));
    }

    private Integer calculateAge(TravelCalculatePremiumRequest request) {
        Period period = Period.between(request.getPersonBirthDate(), request.getAgreementDateFrom());
        return period.getYears();
    }

    private BigDecimal findCountryDefaultDayRate(TravelCalculatePremiumRequest request) {
        return countryDefaultDayRateRepository.findByCountryIc(request.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate not found by country id = " + request.getCountry()));
    }

    private BigDecimal calculateDayCount(TravelCalculatePremiumRequest request) {
        var daysBetween = dateTimeUtil.daysCalculator(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
