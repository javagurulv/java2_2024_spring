package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelPremiumUnderwriting {

    @Autowired
    private DateTimeUtil dateTimeUtil;

    public BigDecimal calculatedPremium(TravelCalculatePremiumRequest premiumRequest) {
        long daysFromTo = dateTimeUtil.calculateDateFromTo(premiumRequest.getAgreementDateFrom(), premiumRequest.getAgreementDateTo());
        return new BigDecimal(daysFromTo);

    }

}
