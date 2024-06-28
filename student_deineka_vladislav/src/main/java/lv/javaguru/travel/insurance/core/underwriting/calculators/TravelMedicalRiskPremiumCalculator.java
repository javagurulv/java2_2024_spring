package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired
    private DateTimeUtil dateTimeUtil;
    @Autowired
    private CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest premiumRequest) {
        var daysCount= calculateDayCount(premiumRequest);
        var countryDefaultRate = countryDefaultDayRate(premiumRequest);
        return countryDefaultRate.multiply(daysCount)
                .setScale(2, RoundingMode.HALF_UP);
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

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
