package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
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

    @Override
    public BigDecimal calculateRiskPremium(TravelCalculatePremiumRequest request) {
        BigDecimal days = calculateDays(request);
        BigDecimal dayRate = findCountryDefaultDayRate(request);
        return days.multiply(dayRate);
    }
    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

    private BigDecimal calculateDays (TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        long differenceBetweenDays = dateTimeUtil.calculateDifferenceBetweenDays(agreementDateFrom, agreementDateTo);
        return BigDecimal.valueOf(differenceBetweenDays);
    }

    private BigDecimal findCountryDefaultDayRate (TravelCalculatePremiumRequest request) {
        String country_ic = request.getCountry();
        return countryDefaultDayRepository.findByCountryIc(country_ic)
                .map(CountryDefaultDayRate::getDefaultDayRate)
                .orElseThrow(() -> new RuntimeException("Country ic = " + country_ic + "default day rate not found!"));
    }

}
