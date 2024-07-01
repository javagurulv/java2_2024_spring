package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.checkerframework.checker.units.qual.A;
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

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest premiumRequest) {
        var daysCount= calculateDayCount(premiumRequest);
        var countryDefaultRate = countryDefaultDayRate(premiumRequest);
        var ageCoefficient = ageCoefficient(premiumRequest);
        return countryDefaultRate.multiply(daysCount).multiply(ageCoefficient).setScale(2, RoundingMode.HALF_UP);
    }


    private BigDecimal calculateDayCount(TravelCalculatePremiumRequest premiumRequest) {
        var daysFromTo = dateTimeUtil.calculateDateFromTo(premiumRequest.getAgreementDateFrom(), premiumRequest.getAgreementDateTo());
        return new BigDecimal(daysFromTo);
    }

    private BigDecimal countryDefaultDayRate(TravelCalculatePremiumRequest premiumRequest) {
        return countryDefaultDayRateRepository.findByCountryIc(premiumRequest.getCountry())
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country day rate is not found by country id = " + premiumRequest.getCountry()));
    }

    private BigDecimal ageCoefficient(TravelCalculatePremiumRequest premiumRequest) {
        int age = countAge(premiumRequest);
        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Age coefficient not found for age = " + age));
    }

    private Integer countAge(TravelCalculatePremiumRequest premiumRequest) {
        LocalDate birthDate = localDate(premiumRequest.getPersonBirthDate());
        LocalDate today = localDate(dateTimeUtil.getTodayDateTime());
        return Period.between(birthDate, today).getYears();
    }

    private LocalDate localDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
