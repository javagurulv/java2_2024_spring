package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class DayCountCalculator {

    @Autowired
    private DateTimeUtil dateTimeUtil;

    BigDecimal calculateDayCount(AgreementDTO agreement) {
        Date agreementDateFrom = agreement.agreementDateFrom();
        Date agreementDateTo = agreement.agreementDateTo();
        long differenceBetweenDays = dateTimeUtil
                .calculateDifferenceBetweenDatesInDays(agreementDateFrom, agreementDateTo);
        return BigDecimal.valueOf(differenceBetweenDays);
    }

}
