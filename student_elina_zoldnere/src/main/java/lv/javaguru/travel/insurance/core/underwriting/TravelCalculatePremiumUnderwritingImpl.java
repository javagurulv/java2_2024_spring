package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class TravelCalculatePremiumUnderwritingImpl implements TravelCalculatePremiumUnderwriting {

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Override
    public BigDecimal calculateAgreementPrice(Date agreementDateFrom, Date agreementDateTo) {
        long differenceBetweenDays = dateTimeUtil.calculateDifferenceBetweenDays(agreementDateFrom, agreementDateTo);
        return BigDecimal.valueOf(differenceBetweenDays);
    }

}
