package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRepository;
    @Autowired
    private AgeCoefficientRepository ageCoefficientRepository;

    @Override
    public BigDecimal calculateRiskPremium(TravelCalculatePremiumRequest request) {
        BigDecimal days = calculateDays(request);
        BigDecimal countryDefaultDayRate = findCountryDefaultDayRate(request);
        BigDecimal ageCoefficient = findAgeCoefficient(request);
        return countryDefaultDayRate
                .multiply(days)
                .multiply(ageCoefficient);
    }

    private BigDecimal calculateDays(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        long differenceBetweenDays = dateTimeUtil
                .calculateDifferenceBetweenDatesInDays(agreementDateFrom, agreementDateTo);
        return BigDecimal.valueOf(differenceBetweenDays);
    }

    private BigDecimal findCountryDefaultDayRate(TravelCalculatePremiumRequest request) {
        String country_ic = request.getCountry();
        return countryDefaultDayRepository.findByCountryIc(country_ic)
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country ic = " + country_ic + "default day rate not found!"));
    }

    private BigDecimal findAgeCoefficient(TravelCalculatePremiumRequest request) {
        Date personBirthDate = request.getPersonBirthDate();
        Date currentDate = dateTimeUtil.currentTimeToday(); // hours and seconds does not matter
        Integer age = dateTimeUtil.calculateDifferenceBetweenDatesInYears(personBirthDate, currentDate);

        return ageCoefficientRepository.findCoefficient(age)
                .map(AgeCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Coefficient for age = " + age + "not found!"));
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
