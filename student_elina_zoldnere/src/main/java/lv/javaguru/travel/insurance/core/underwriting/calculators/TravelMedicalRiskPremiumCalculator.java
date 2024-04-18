package lv.javaguru.travel.insurance.core.underwriting.calculators;

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

    @Override
    public BigDecimal calculateRiskPremium(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        long differenceBetweenDays = dateTimeUtil.calculateDifferenceBetweenDays(agreementDateFrom, agreementDateTo);
        return BigDecimal.valueOf(differenceBetweenDays);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
