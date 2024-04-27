package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class DayCountCalculator {

    @Autowired
    private DateTimeUtil dateTimeUtil;

    BigDecimal calculateDayCount(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        long differenceBetweenDays = dateTimeUtil
                .calculateDifferenceBetweenDatesInDays(agreementDateFrom, agreementDateTo);
        return BigDecimal.valueOf(differenceBetweenDays);
    }

}
