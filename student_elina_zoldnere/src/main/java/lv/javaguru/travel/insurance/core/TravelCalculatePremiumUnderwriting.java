package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class TravelCalculatePremiumUnderwriting {

    @Autowired
    private DateTimeUtil dateTimeService;

    public BigDecimal calculateAgreementPrice(Date agreementDateFrom, Date agreementDateTo) {
        long differenceBetweenDays = dateTimeService.calculateDifferenceBetweenDays(agreementDateFrom, agreementDateTo);
        return BigDecimal.valueOf(differenceBetweenDays);
    }

}
